package com.zkzl.module.pro.service.product;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import com.zkzl.framework.security.core.util.SecurityFrameworkUtils;
import com.zkzl.module.pro.controller.admin.product.vo.*;
import com.zkzl.module.pro.controller.app.product.vo.ProductDescVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductReqVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductVO;
import com.zkzl.module.pro.convert.product.ProductConvert;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import com.zkzl.module.pro.dal.dataobject.productcertificate.ProductCertificateDO;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import com.zkzl.module.pro.dal.dataobject.supplyinfo.SupplyInfoDO;
import com.zkzl.module.pro.dal.mysql.product.ProductMapper;
import com.zkzl.module.pro.dal.mysql.productcertificate.ProductCertificateMapper;
import com.zkzl.module.pro.dal.mysql.productparameters.ProductParametersMapper;
import com.zkzl.module.pro.dal.mysql.productpic.ProductPicMapper;
import com.zkzl.module.pro.dal.mysql.supplyinfo.SupplyInfoMapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRODUCT_NOT_EXISTS;

/**
 * 产品 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper ductMapper;
    @Resource
    private ProductParametersMapper productParametersMapper;
    @Resource
    private ProductPicMapper productPicMapper;
    @Resource
    private ProductCertificateMapper productCertificateMapper;
    @Resource
    private SupplyInfoMapper supplyInfoMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createduct(ProductCreateReqVO createReqVO) {
        // 产品id
        String productId = IdUtil.getSnowflakeNextIdStr();
        createReqVO.setProductId(productId);
        // 用途分类中文
        if(createReqVO.getUseClassificationCns() != null){
            createReqVO.setUseClassificationCn(createReqVO.getUseClassificationCns().toString().substring(1,createReqVO.getUseClassificationCns().toString().length()-1));
        }
        // 用途分类英文
        if(createReqVO.getUseClassificationEns() != null){
            createReqVO.setUseClassificationEn(createReqVO.getUseClassificationEns().toString().substring(1,createReqVO.getUseClassificationEns().toString().length()-1));
        }
        // 插入
        //插入产品参数
        addProductParameters(createReqVO.getProductParametersDOS(), productId, 0L);
        //插入产品图片
        if (createReqVO.getPicDOS() != null) {
            List<ProductPicDO> productPicDOList = createReqVO.getPicDOS().stream()
                    .map(pic -> pic.setProductId(productId)).collect(Collectors.toList());
            productPicMapper.insertBatch(productPicDOList);
        }
        //插入产品证书
        if (createReqVO.getProductCertificateDOS() != null) {
            List<ProductCertificateDO> productCertificateDOList = createReqVO.getProductCertificateDOS().stream()
                    .map(certificate -> certificate.setProductId(productId)).collect(Collectors.toList());
            productCertificateMapper.insertBatch(productCertificateDOList);
        }
        ProductDO duct = ProductConvert.INSTANCE.convert(createReqVO);
        ductMapper.insert(duct);
        // 返回
        return duct.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateduct(ProductUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductExists(updateReqVO.getProductId());
        //删除以前的关联数据
        productParametersMapper.delete(new QueryWrapper<ProductParametersDO>().lambda().eq(ProductParametersDO::getProductId,updateReqVO.getProductId()));
        productPicMapper.delete(new QueryWrapper<ProductPicDO>().lambda().eq(ProductPicDO::getProductId,updateReqVO.getProductId()));
        productCertificateMapper.delete(new QueryWrapper<ProductCertificateDO>().lambda().eq(ProductCertificateDO::getProductId,updateReqVO.getProductId()));
        // 更新

        // 用途分类中文
        if(updateReqVO.getUseClassificationCns() != null){
            updateReqVO.setUseClassificationCn(updateReqVO.getUseClassificationCns().toString().substring(1,updateReqVO.getUseClassificationCns().toString().length()-1));
        }
        // 用途分类英文
        if(updateReqVO.getUseClassificationEns() != null){
            updateReqVO.setUseClassificationEn(updateReqVO.getUseClassificationEns().toString().substring(1,updateReqVO.getUseClassificationEns().toString().length()-1));
        }

        //插入产品参数
        addProductParameters(updateReqVO.getProductParametersDOS(),updateReqVO.getProductId(),0L);
        //插入产品图片
        if (updateReqVO.getPicDOS() != null) {
            List<ProductPicDO> productPicDOList = updateReqVO.getPicDOS().stream()
                    .map(pic -> pic.setProductId(updateReqVO.getProductId())).collect(Collectors.toList());
            productPicMapper.insertBatch(productPicDOList);
        }
        //插入产品证书
        if (updateReqVO.getProductCertificateDOS() != null) {
            List<ProductCertificateDO> productCertificateDOList = updateReqVO.getProductCertificateDOS().stream()
                    .map(certificate -> certificate.setProductId(updateReqVO.getProductId())).collect(Collectors.toList());
            productCertificateMapper.insertBatch(productCertificateDOList);
        }
        ProductDO updateObj = ProductConvert.INSTANCE.convert(updateReqVO);
        ductMapper.update(updateObj,new UpdateWrapper<ProductDO>().lambda().eq(ProductDO::getProductId,updateReqVO.getProductId()));
    }

    @Override
    public void deleteduct(String id) {
        // 校验存在
        this.validateductExists(id);
        // 删除
        productParametersMapper.delete(new QueryWrapper<ProductParametersDO>().lambda().eq(ProductParametersDO::getProductId,id));
        productPicMapper.delete(new QueryWrapper<ProductPicDO>().lambda().eq(ProductPicDO::getProductId,id));
        productCertificateMapper.delete(new QueryWrapper<ProductCertificateDO>().lambda().eq(ProductCertificateDO::getProductId,id));
        ductMapper.delete(new QueryWrapper<ProductDO>().lambda().eq(ProductDO::getProductId, id));
    }

    private void validateductExists(String id) {
        ProductDO productDO = ductMapper.selectOne(new QueryWrapper<ProductDO>().lambda().eq(ProductDO::getProductId, id));
        if (productDO == null) {
            throw exception(PRODUCT_NOT_EXISTS);
        }
    }

    @Override
    public ProductDO getduct(Long id) {
        return ductMapper.selectById(id);
    }

    @Override
    public List<ProductDO> getductList(String typeId) {
        if("".equals(typeId)) typeId = null;
        return ductMapper.selectList(new LambdaQueryWrapperX<ProductDO>().eqIfPresent(ProductDO::getTypeId,typeId));
    }

    @Override
    public PageResult<ProductDO> getductPage(ProductPageReqVO pageReqVO) {
        PageResult<ProductDO> result = ductMapper.selectPage(pageReqVO);
        List<ProductDO> resultList = result.getList();
        List<ProductPicDO> picDO;
        for (ProductDO productDO : resultList) {
            picDO = productPicMapper.selectList(new LambdaQueryWrapperX<ProductPicDO>()
                    .eq(ProductPicDO::getProductId,productDO.getProductId())
                    .last("limit 1"));
            if (null == picDO){
                continue;
            }
            productDO.setPicDOS(picDO);
        }
        List<SupplyInfoDO> supplyers = null;
        String resultSupplyers = "";
        for (ProductDO productDO : resultList) {
            supplyers = supplyInfoMapper.selectList(new LambdaQueryWrapper<SupplyInfoDO>().eq(SupplyInfoDO::getProductId,productDO.getProductId()));
            if (supplyers.size() == 0){
                productDO.setSupply("暂无");
            }else{
                resultSupplyers = "";
                for (SupplyInfoDO supplyer : supplyers) {
                    resultSupplyers += (supplyer.getName()+"、");
                }
                StringUtils.removeEnd(resultSupplyers,"、");
                productDO.setSupply(resultSupplyers);
            }
        }

        return result;
    }

    @Override
    public List<ProductDO> getductList(ProductExportReqVO exportReqVO) {
        return ductMapper.selectList(exportReqVO);
    }

    @Override
    public List<ProductExcelVO2> getductList2(ProductExportReqVO exportReqVO) {
        return ductMapper.selectList2(exportReqVO);
    }

    @Override
    public ProductRespVO getProduct(String productId) {
        ProductRespVO product = ductMapper.getProduct(productId);
        product.setProductParametersDOS(streamMethod(0L, product.getProductParametersDOS()));
        // 用途分类中文
        if(product.getUseClassificationCn() != null){
            product.setUseClassificationCns(Arrays.asList(product.getUseClassificationCn().split(",")));
        }
        return product;
    }

    @Override
    public void updateShipped(String productId) {
        ProductDO productDO = ductMapper.selectOne(new QueryWrapper<ProductDO>().lambda().eq(ProductDO::getProductId, productId));
        if (productDO == null) {
            throw exception(PRODUCT_NOT_EXISTS);
        }
        if ("1".equals(productDO.getShipped())) {
            productDO.setShipped("0");
        } else {
            productDO.setShipped("1");
        }
        ductMapper.update(productDO, new UpdateWrapper<ProductDO>().lambda().eq(ProductDO::getProductId, productId));
    }

    private void addProductParameters(List<ProductParametersDO> parametersVOList, String productId, Long parentId) {
        for (ProductParametersDO parametersDO : parametersVOList) {
            parametersDO.setProductId(productId);
            parametersDO.setParentParamId(parentId);
            productParametersMapper.insert(parametersDO);
            if (parametersDO.getParametersDOS() != null) {
                addProductParameters(parametersDO.getParametersDOS(), productId, parametersDO.getId());
            }
        }
    }


    /**
     * stream 方法转换树形结构方法的优化
     *
     * @param parentId
     * @param treeList
     * @return
     */
    public static List<ProductParametersDO> streamMethod(Long parentId, List<ProductParametersDO> treeList) {
        List<ProductParametersDO> list = treeList.stream()
                // 筛选父节点
                .filter(t -> t.getParentParamId().equals(parentId))
                // 递归设置子节点
                .map(item -> {
                    item.setParametersDOS(streamMethod(item.getId(), treeList));
                    return item;
                })
                .collect(Collectors.toList());
        return list;
    }


    @Override
    public PageResult<ProductDescVO> pageApp(ProductReqVO productReqVO) {
        IPage<ProductDescVO> mPage = MyBatisUtils.buildPage(productReqVO);
        ductMapper.pageApp(mPage,productReqVO);
        // 设置产品长宽高
        return getProductDescVOPageResult(mPage);
    }

    @Override
    public ProductDescVO getDesc(Long id) {
        ProductDescVO productDescVO = ductMapper.getDesc(id);
        if (productDescVO.getBoxGauge().contains("*") && productDescVO.getBoxGauge().contains("定制")){
            productDescVO.setBoxLength(BigDecimal.valueOf(0));
            productDescVO.setBoxWide(BigDecimal.valueOf(0));
            productDescVO.setBoxHeight(BigDecimal.valueOf(0));
        }else {
            String[] split = productDescVO.getBoxGauge().split("\\*");
            if (split.length == 3) {
                productDescVO.setBoxLength(new BigDecimal(split[0]));
                productDescVO.setBoxWide(new BigDecimal(split[1]));
                productDescVO.setBoxHeight(new BigDecimal(split[2]));
            } else {
                productDescVO.setBoxLength(BigDecimal.valueOf(0));
                productDescVO.setBoxWide(BigDecimal.valueOf(0));
                productDescVO.setBoxHeight(BigDecimal.valueOf(0));
            }
        }
        // 设置是否已加入询价标志
        /*Long userId = SecurityFrameworkUtils.getLoginUserId();
        productDescVO.setIsInqury(ductMapper.getIsInqury(productDescVO.getProductId(),userId));*/
        return productDescVO;
    }

    @Override
    public PageResult<ProductDescVO> recommend(Long id) {
        ProductDO product = ductMapper.selectById(id);

        ProductReqVO pageParam = new ProductReqVO();
        pageParam.setTypeId(product.getTypeId())
                .setId(id);

        IPage<ProductDescVO> mPage = MyBatisUtils.buildPage(pageParam);
        ductMapper.recommend(mPage,pageParam);

        if (mPage.getRecords().size() == 0){
            ductMapper.pageApp(mPage,new ProductReqVO());
        }
        // 设置产品长宽高
        return getProductDescVOPageResult(mPage);
    }

    @NotNull
    private PageResult<ProductDescVO> getProductDescVOPageResult(IPage<ProductDescVO> mPage) {
        mPage.getRecords().forEach(ProductDescVO->{
            if (ProductDescVO.getBoxGauge().contains("*") && ProductDescVO.getBoxGauge().contains("定制")){
                ProductDescVO.setBoxLength(BigDecimal.valueOf(0));
                ProductDescVO.setBoxWide(BigDecimal.valueOf(0));
                ProductDescVO.setBoxHeight(BigDecimal.valueOf(0));
            }else{
                String[] split = ProductDescVO.getBoxGauge().split("\\*");
                if(split.length ==3){
                    ProductDescVO.setBoxLength(new BigDecimal(split[0]));
                    ProductDescVO.setBoxWide(new BigDecimal(split[1]));
                    ProductDescVO.setBoxHeight(new BigDecimal(split[2].trim()));
                }else {
                    ProductDescVO.setBoxLength(BigDecimal.valueOf(0));
                    ProductDescVO.setBoxWide(BigDecimal.valueOf(0));
                    ProductDescVO.setBoxHeight(BigDecimal.valueOf(0));
                }
            }
        });

        return new PageResult<>(mPage.getRecords(), mPage.getTotal());
    }

}
