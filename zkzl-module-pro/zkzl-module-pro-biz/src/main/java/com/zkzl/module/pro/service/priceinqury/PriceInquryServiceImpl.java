package com.zkzl.module.pro.service.priceinqury;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.common.pojo.PageParam;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import com.zkzl.framework.security.core.util.SecurityFrameworkUtils;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.PriceInquryChildCreateReqVO;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.PriceInquryChildExportReqVO;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.PriceInquryChildUpdateReqVO;
import com.zkzl.module.pro.controller.app.priceinqury.vo.PriceInquryHistoryVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductDescVO;
import com.zkzl.module.pro.convert.priceinqurychild.PriceInquryChildConvert;
import com.zkzl.module.pro.dal.dataobject.order.OrderDO;
import com.zkzl.module.pro.dal.dataobject.ordergoods.OrderGoodsDO;
import com.zkzl.module.pro.dal.dataobject.ordersummary.OrderSummaryDO;
import com.zkzl.module.pro.dal.dataobject.priceinqurychild.PriceInquryChildDO;
import com.zkzl.module.pro.dal.dataobject.procurementsummary.ProcurementSummaryDO;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import com.zkzl.module.pro.dal.dataobject.supplyinfo.SupplyInfoDO;
import com.zkzl.module.pro.dal.mysql.order.ProOrderMapper;
import com.zkzl.module.pro.dal.mysql.ordergoods.OrderGoodsMapper;
import com.zkzl.module.pro.dal.mysql.ordersummary.OrderSummaryMapper;
import com.zkzl.module.pro.dal.mysql.priceinqurychild.PriceInquryChildMapper;
import com.zkzl.module.pro.dal.mysql.procurementsummary.ProcurementSummaryMapper;
import com.zkzl.module.pro.dal.mysql.product.ProductMapper;
import com.zkzl.module.pro.dal.mysql.supplyinfo.SupplyInfoMapper;
import com.zkzl.module.system.dal.mysql.user.AdminUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import com.zkzl.module.pro.controller.admin.priceinqury.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqury.PriceInquryDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.priceinqury.PriceInquryConvert;
import com.zkzl.module.pro.dal.mysql.priceinqury.PriceInquryMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.*;

/**
 * 询价 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class PriceInquryServiceImpl implements PriceInquryService {

    @Resource
    private PriceInquryMapper priceInquryMapper;

    @Resource
    private PriceInquryChildMapper priceInquryChildMapper;

    @Resource
    private SupplyInfoMapper supplyInfoMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProOrderMapper proOrderMapper;
    @Resource
    private OrderGoodsMapper orderGoodsMapper;
    @Resource
    private OrderSummaryMapper orderSummaryMapper;
    @Resource
    private ProcurementSummaryMapper procurementSummaryMapper;
    @Resource
    private AdminUserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createPriceInqury(PriceInquryCreateReqVO createReqVO) {
        // 插入
        PriceInquryDO priceInqury = PriceInquryConvert.INSTANCE.convert(createReqVO);
        priceInqury.setPriceInquryId(IdUtil.getSnowflakeNextIdStr());//业务id
        priceInquryMapper.insert(priceInqury);
        if (ObjectUtil.isEmpty(createReqVO.getPriceInquryChilds())){
            throw exception(PRICE_INQURY_CHILD_NOT_EXISTS);
        }
        //创建询价单子表
        PriceInquryChildDO entity;
        for (PriceInquryChildCreateReqVO child : createReqVO.getPriceInquryChilds()) {
            entity = PriceInquryChildConvert.INSTANCE.convert(child);
            entity.setPriceInquryId(priceInqury.getPriceInquryId());
            priceInquryChildMapper.insert(entity);
        }
        // 返回
        return priceInqury.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePriceInqury(PriceInquryUpdateReqVO updateReqVO) {
        // 校验存在
        this.validatePriceInquryExists(updateReqVO.getId());
        // 更新
        PriceInquryDO updateObj = PriceInquryConvert.INSTANCE.convert(updateReqVO);
        //更新子表
        if (ObjectUtil.isNotEmpty(updateReqVO.getPriceInquryChilds())){
            List<PriceInquryChildUpdateReqVO> updateVOs = updateReqVO.getPriceInquryChilds();
            for (PriceInquryChildUpdateReqVO updateVO : updateVOs) {
                priceInquryChildMapper.updateById(PriceInquryChildConvert.INSTANCE.convert(updateVO));
            }
        }
        //status=3 询价单成交 同时1创建订单 2创建订单汇总 3创建采购汇总
        if ("3".equals(updateReqVO.getStatus())){
            PriceInquryDO currentInqury = priceInquryMapper.selectById(updateReqVO.getId());
            if(StringUtils.isEmpty(currentInqury.getSellerContact())
                ||StringUtils.isEmpty(currentInqury.getSellerCompanyAddress())
                ||StringUtils.isEmpty(currentInqury.getSellerCompanyName())
                ||StringUtils.isEmpty(currentInqury.getSellerTel())){
                throw exception(PRICE_INQURY_NEED_SELLER_MES);
            }
            //1创建订单
            OrderDO insertOrder = inqury2Order(currentInqury);
            insertOrder.setOrderId(IdUtil.getSnowflakeNextIdStr());
            proOrderMapper.insert(insertOrder);

            //批量插入订单子表
            List<PriceInquryChildDO> childs = priceInquryChildMapper.selectList(
                    new PriceInquryChildExportReqVO().setPriceInquryId(currentInqury.getPriceInquryId()));
            List<OrderGoodsDO> insertGoods = inquryChild2OrderGoods(childs,insertOrder.getOrderId());
            orderGoodsMapper.insertBatch(insertGoods);

            //2创建订单汇总
            //String customer = userMapper.selectById(insertOrder.getUserId()).getUsername();
            String customer = updateObj.getBuyerCompanyName();
            //String customer = "ccc";
            OrderSummaryDO insertSummary = new OrderSummaryDO();
            insertSummary.setOrderSummaryId(IdUtil.getSnowflakeNextIdStr())
                    .setOrderId(insertOrder.getOrderId())
                    .setPriceInquryId(insertOrder.getPriceInquryId())
                    .setCustomer(customer);
            orderSummaryMapper.insert(insertSummary);

            //3创建采购汇总
            ProcurementSummaryDO insertProcurement;
            for (OrderGoodsDO insertGood : insertGoods) {
                insertProcurement = new ProcurementSummaryDO();
                insertProcurement.setProcurementSummaryId(IdUtil.getSnowflakeNextIdStr())
                        .setSupplyCompany(insertGood.getSupplyCompany())
                        /*.setProcurementAmount(insertGood.getPrice())*/
                        .setOrderId(insertGood.getOrderId())
                        .setOrderChildId(insertGood.getOrderChildId());
                procurementSummaryMapper.insert(insertProcurement);
            }

        }

        priceInquryMapper.updateById(updateObj);
    }


    @Override
    public void deletePriceInqury(Long id) {
        // 校验存在
        this.validatePriceInquryExists(id);
        // 删除
        priceInquryMapper.deleteById(id);
    }

    private void validatePriceInquryExists(Long id) {
        if (priceInquryMapper.selectById(id) == null) {
            throw exception(PRICE_INQURY_NOT_EXISTS);
        }
    }

    @Override
    public PriceInquryDO getPriceInqury(Long id) {
        return priceInquryMapper.selectById(id);
    }

    @Override
    public List<PriceInquryDO> getPriceInquryList(Collection<Long> ids) {
        return priceInquryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PriceInquryDO> getPriceInquryPage(PriceInquryPageReqVO pageReqVO) {
        return priceInquryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PriceInquryDO> getPriceInquryList(PriceInquryExportReqVO exportReqVO) {
        return priceInquryMapper.selectList(exportReqVO);
    }

    @Override
    public PageResult<PriceInquryPageVO> pageManage(PriceInquryPageReqVO pageVO) {
        IPage<PriceInquryPageVO> mPage = MyBatisUtils.buildPage(pageVO);
        priceInquryMapper.pageManage(mPage,pageVO);
        return new PageResult<>(mPage.getRecords(),mPage.getTotal());
    }

    @Override
    public PageResult<PriceInquryPageVO> pageCommon(PriceInquryPageReqVO pageVO) {
        pageVO.setLoginUserId(SecurityFrameworkUtils.getLoginUserId());
        IPage<PriceInquryPageVO> mPage = MyBatisUtils.buildPage(pageVO);
        priceInquryMapper.pageCommon(mPage,pageVO);
        return new PageResult<>(mPage.getRecords(),mPage.getTotal());
    }

    @Override
    public PriceInquryAndChildsVO getPriceInquryAndChilds(Long id) {
        PriceInquryAndChildsVO result = priceInquryMapper.getPriceInquryAndChilds(id);

        /*for (PriceInquryChildsVO child : result.getChilds()) {
            List<PriceInquryChildsSupplyerVO> supplys = supplyInfoMapper.getSupplyByProductId(child.getProductId());
            if (ObjectUtil.isEmpty(supplys)){
                log.info("产品:"+child.getProductId()+" 无法查询供应商！");
                continue;
            }
            child.setSupplyName(supplys.get(0).getName());
        }*/
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPriceInqury(PriceInquryCreateReqVO param) {

        Long LoginUserId = SecurityFrameworkUtils.getLoginUserId();
        Boolean isNewInqury = false;//是否为新建的询价单-不是新的询价单则需验证子表产品是否已加入询价

        List<String> status = new ArrayList<>();
        status.add("0");
        status.add("1");
        status.add("2");
        status.add("5");
        //查询登陆人是否有正在询价的询价单
        //原则上 询价单每个人只存在一条正在询价的询价单(status=0,1,2,5)
        PriceInquryDO inquryIng = priceInquryMapper.selectOne(new LambdaQueryWrapper<PriceInquryDO>()
                .eq(PriceInquryDO::getBuyerCompanyId,LoginUserId)
                .in(PriceInquryDO::getStatus,status));
        if (ObjectUtil.isEmpty(inquryIng)){
            isNewInqury = true;
            //没有正在创建的询价单则创建
            inquryIng = PriceInquryConvert.INSTANCE.convert(param);
            inquryIng.setPriceInquryId(IdUtil.getSnowflakeNextIdStr())
                    .setBuyerCompanyId(LoginUserId);
            priceInquryMapper.insert(inquryIng);
        }else {
            throw exception(PRICE_INQURY_INQUIRY_UNFINISHED);
        }

        if (ObjectUtil.isEmpty(param.getPriceInquryChilds())){
            throw exception(PRICE_INQURY_CHILD_NOT_EXISTS);
        }

        List<PriceInquryChildDO> priceInquryChildDOList = new ArrayList<>();
        List<PriceInquryChildDO> inquryChildsIng = null;
        for (PriceInquryChildCreateReqVO child : param.getPriceInquryChilds()) {

            if (!isNewInqury){
                //添加询价子单前查询是否已存在此产品的询价信息
                inquryChildsIng = priceInquryChildMapper.selectList(new LambdaQueryWrapper<PriceInquryChildDO>()
                        .eq(PriceInquryChildDO::getPriceInquryId,inquryIng.getPriceInquryId())
                        .eq(PriceInquryChildDO::getProductId,child.getProductId()));
            }

            if (ObjectUtil.isNotEmpty(inquryChildsIng)){
                throw exception(PRODUCT_ALREADY_EXISTS);
            }
            // 查询产品详情
            ProductDO productDO = productMapper.selectOne(new LambdaQueryWrapperX<ProductDO>().eq(ProductDO::getProductId, child.getProductId()));
            if (ObjectUtil.isEmpty(productDO)){
                throw exception(PRODUCT_NOT_EXISTS);
            }
            // 将产品信息添加到子询价单
            PriceInquryChildDO priceInquryChildDO = new PriceInquryChildDO();
            priceInquryChildDO.setPriceInquryId(inquryIng.getPriceInquryId());
            priceInquryChildDO.setProductId(child.getProductId());
            priceInquryChildDO.setHsSerial(productDO.getHsNo());
            priceInquryChildDO.setProductSize(productDO.getSize());
            priceInquryChildDO.setProductColor(productDO.getColourCn());
            priceInquryChildDO.setProductG(productDO.getGramWeight());
            priceInquryChildDO.setPackageWay(productDO.getPackagingMethodCn());
            String[] boxGauge = productDO.getBoxGauge().split("\\*");
            if(boxGauge.length ==3){
                priceInquryChildDO.setBoxLength(new BigDecimal(boxGauge[0]));
                priceInquryChildDO.setBoxWide(new BigDecimal(boxGauge[1]));
                priceInquryChildDO.setBoxHeight(new BigDecimal(boxGauge[2]));
            }else {
                priceInquryChildDO.setBoxLength(BigDecimal.valueOf(0));
                priceInquryChildDO.setBoxWide(BigDecimal.valueOf(0));
                priceInquryChildDO.setBoxHeight(BigDecimal.valueOf(0));
            }
            priceInquryChildDO.setVolume(productDO.getVolume());
            priceInquryChildDO.setGrossWeight(productDO.getGrossWeight());
            priceInquryChildDO.setNetWeight(productDO.getNetWeight());
            priceInquryChildDO.setMount(child.getMount());

            priceInquryChildDOList.add(priceInquryChildDO);
            //查询产品的供应商
            /*SupplyInfoDO supplyInfo= supplyInfoMapper.selectOne("product_id",child.getProductId());
            if (ObjectUtil.isEmpty(supplyInfo)){
                throw exception(PRODUCT_HAVE_NOT_SUPPLY);
            }

            entity = PriceInquryChildConvert.INSTANCE.convert(child);
            entity.setPriceInquryId(inquryIng.getPriceInquryId())
                .setSupplyInfoId(supplyInfo.getSupplyInfoId());
            priceInquryChildMapper.insert(entity);*/
        }
        // 批处理插入
        priceInquryChildMapper.insertBatch(priceInquryChildDOList);
    }

    @Override
    public PageResult<PriceInquryHistoryVO> inquryHistory(PageParam param) {
        IPage<PriceInquryHistoryVO> mPage = MyBatisUtils.buildPage(param);
        priceInquryMapper.inquryHistory(mPage,param,SecurityFrameworkUtils.getLoginUserId());
        return new PageResult<>(mPage.getRecords(), mPage.getTotal());
    }


    /**
     * 询价单转订单
     * @param inquryDO
     * @return
     */
    private OrderDO inqury2Order(PriceInquryDO inquryDO){
        OrderDO result = new OrderDO();
        result.setPriceInquryId(inquryDO.getPriceInquryId())
                .setPrice(inquryDO.getPrice())
                .setDiscount(inquryDO.getDiscount())
                .setPriceDate(inquryDO.getPriceDate())
                .setEffectiveDate(inquryDO.getEffectiveDate())
                .setSellerCompanyAddress(inquryDO.getSellerCompanyAddress())
                .setSellerCompanyName(inquryDO.getSellerCompanyName())
                .setSellerTel(inquryDO.getSellerTel())
                .setSellerContact(inquryDO.getSellerContact())
                .setTotal(inquryDO.getTotal())
                .setUserId(inquryDO.getBuyerCompanyId());
        return result;
    }

    /**
     * 询价子单转订单子单
     * @param childs
     * @return
     */
    private List<OrderGoodsDO> inquryChild2OrderGoods(List<PriceInquryChildDO> childs,String orderId){
        List<OrderGoodsDO> results = new CopyOnWriteArrayList<>();
        OrderGoodsDO result;
        for (PriceInquryChildDO child : childs) {
            result = new OrderGoodsDO();
            result.setPrice(child.getPrice())
                    .setUnitPrice(child.getUnitPrice())
                    .setMount(child.getMount())
                    .setBoxHeight(child.getBoxHeight())
                    .setBoxLength(child.getBoxLength())
                    .setBoxWide(child.getBoxWide())
                    .setProductDesc(child.getProductDesc())
                    .setGrossWeight(child.getGrossWeight())
                    .setNetWeight(child.getNetWeight())
                    .setHsSerial(child.getHsSerial())
                    .setOrderId(orderId)
                    .setPackageWay(child.getPackageWay())
                    .setVolume(child.getVolume())
                    .setRemark(child.getRemark())
                    .setProductId(child.getProductId())
                    .setProductG(child.getProductG())
                    .setProductColor(child.getProductColor())
                    .setProductSize(child.getProductSize())
                    .setSupplyCompany(supplyInfoMapper.selectOne("supply_info_id",child.getSupplyInfoId()).getName())
                    .setOrderChildId(IdUtil.getSnowflakeNextIdStr());
            results.add(result);
        }
        return results;
    }
}
