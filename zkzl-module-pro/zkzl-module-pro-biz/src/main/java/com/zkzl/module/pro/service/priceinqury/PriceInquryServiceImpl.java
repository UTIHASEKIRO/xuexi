package com.zkzl.module.pro.service.priceinqury;


import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import com.zkzl.framework.security.core.util.SecurityFrameworkUtils;
import com.zkzl.module.pro.controller.admin.ordersummary.vo.OrderSummaryCreateReqVO;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.PriceInquryChildCreateReqVO;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.PriceInquryChildExportReqVO;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.PriceInquryChildUpdateReqVO;
import com.zkzl.module.pro.convert.priceinqurychild.PriceInquryChildConvert;
import com.zkzl.module.pro.dal.dataobject.order.OrderDO;
import com.zkzl.module.pro.dal.dataobject.ordergoods.OrderGoodsDO;
import com.zkzl.module.pro.dal.dataobject.ordersummary.OrderSummaryDO;
import com.zkzl.module.pro.dal.dataobject.priceinqurychild.PriceInquryChildDO;
import com.zkzl.module.pro.dal.dataobject.procurementsummary.ProcurementSummaryDO;
import com.zkzl.module.pro.dal.mysql.order.ProOrderMapper;
import com.zkzl.module.pro.dal.mysql.ordergoods.OrderGoodsMapper;
import com.zkzl.module.pro.dal.mysql.ordersummary.OrderSummaryMapper;
import com.zkzl.module.pro.dal.mysql.priceinqurychild.PriceInquryChildMapper;
import com.zkzl.module.pro.dal.mysql.procurementsummary.ProcurementSummaryMapper;
import com.zkzl.module.pro.dal.mysql.supplyinfo.SupplyInfoMapper;
import com.zkzl.module.system.dal.mysql.user.AdminUserMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import com.zkzl.module.pro.controller.admin.priceinqury.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqury.PriceInquryDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.priceinqury.PriceInquryConvert;
import com.zkzl.module.pro.dal.mysql.priceinqury.PriceInquryMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRICE_INQURY_CHILD_NOT_EXISTS;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRICE_INQURY_NOT_EXISTS;

/**
 * 询价 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PriceInquryServiceImpl implements PriceInquryService {

    @Resource
    private PriceInquryMapper priceInquryMapper;

    @Resource
    private PriceInquryChildMapper priceInquryChildMapper;

    @Resource
    private SupplyInfoMapper supplyInfoMapper;

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
            String customer = "ccc";
            OrderSummaryDO insertSummary = new OrderSummaryDO();
            insertSummary.setOrderSummaryId(IdUtil.getSnowflakeNextIdStr())
                    .setOrderId(insertOrder.getOrderId())
                    .setCustomer(customer);
            orderSummaryMapper.insert(insertSummary);

            //3创建采购汇总
            ProcurementSummaryDO insertProcurement;
            for (OrderGoodsDO insertGood : insertGoods) {
                insertProcurement = new ProcurementSummaryDO();
                insertProcurement.setProcurementSummaryId(IdUtil.getSnowflakeNextIdStr())
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

        for (PriceInquryChildsVO child : result.getChilds()) {
            List<PriceInquryChildsSupplyerVO> supplys = supplyInfoMapper.getSupplyByProductId(child.getProductId());
            child.setSupplyName(supplys.get(0).getName());
        }
        return result;
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
                    .setOrderChildId(IdUtil.getSnowflakeNextIdStr());
            results.add(result);
        }
        return results;
    }
}
