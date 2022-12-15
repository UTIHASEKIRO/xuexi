package com.zkzl.module.pro.dal.mysql.product;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.module.pro.controller.admin.product.vo.ProductExportReqVO;
import com.zkzl.module.pro.controller.admin.product.vo.ProductPageReqVO;
import com.zkzl.module.pro.controller.admin.product.vo.ProductRespVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductDescVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductReqVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductVO;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 产品 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductMapper extends BaseMapperX<ProductDO> {

    default PageResult<ProductDO> selectPage(ProductPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductDO>()
                .eqIfPresent(ProductDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(ProductDO::getProductNameCn, reqVO.getProductNameCn())
                .eqIfPresent(ProductDO::getProductNameEn, reqVO.getProductNameEn())
                .eqIfPresent(ProductDO::getProductModel, reqVO.getProductModel())
                .eqIfPresent(ProductDO::getHsNo, reqVO.getHsNo())
                .eqIfPresent(ProductDO::getPackagingMethodCn, reqVO.getPackagingMethodCn())
                .eqIfPresent(ProductDO::getPackagingMethodEn, reqVO.getPackagingMethodEn())
                .eqIfPresent(ProductDO::getColourCn, reqVO.getColourCn())
                .eqIfPresent(ProductDO::getColourEn, reqVO.getColourEn())
                .eqIfPresent(ProductDO::getUseClassificationCn, reqVO.getUseClassificationCn())
                .eqIfPresent(ProductDO::getUseClassificationEn, reqVO.getUseClassificationEn())
                .eqIfPresent(ProductDO::getDeliveryMethodCn, reqVO.getDeliveryMethodCn())
                .eqIfPresent(ProductDO::getDeliveryMethodEn, reqVO.getDeliveryMethodEn())
                .eqIfPresent(ProductDO::getGramWeight, reqVO.getGramWeight())
                .eqIfPresent(ProductDO::getSize, reqVO.getSize())
                .eqIfPresent(ProductDO::getBoxGauge, reqVO.getBoxGauge())
                .eqIfPresent(ProductDO::getVolume, reqVO.getVolume())
                .eqIfPresent(ProductDO::getGrossWeight, reqVO.getGrossWeight())
                .eqIfPresent(ProductDO::getNetWeight, reqVO.getNetWeight())
                .eqIfPresent(ProductDO::getShipped, reqVO.getShipped())
                .betweenIfPresent(ProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductDO::getId));
    }

    default List<ProductDO> selectList(ProductExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductDO>()
                .eqIfPresent(ProductDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(ProductDO::getProductNameCn, reqVO.getProductNameCn())
                .eqIfPresent(ProductDO::getProductNameEn, reqVO.getProductNameEn())
                .eqIfPresent(ProductDO::getProductModel, reqVO.getProductModel())
                .eqIfPresent(ProductDO::getHsNo, reqVO.getHsNo())
                .eqIfPresent(ProductDO::getPackagingMethodCn, reqVO.getPackagingMethodCn())
                .eqIfPresent(ProductDO::getPackagingMethodEn, reqVO.getPackagingMethodEn())
                .eqIfPresent(ProductDO::getColourCn, reqVO.getColourCn())
                .eqIfPresent(ProductDO::getColourEn, reqVO.getColourEn())
                .eqIfPresent(ProductDO::getUseClassificationCn, reqVO.getUseClassificationCn())
                .eqIfPresent(ProductDO::getUseClassificationEn, reqVO.getUseClassificationEn())
                .eqIfPresent(ProductDO::getDeliveryMethodCn, reqVO.getDeliveryMethodCn())
                .eqIfPresent(ProductDO::getDeliveryMethodEn, reqVO.getDeliveryMethodEn())
                .eqIfPresent(ProductDO::getGramWeight, reqVO.getGramWeight())
                .eqIfPresent(ProductDO::getSize, reqVO.getSize())
                .eqIfPresent(ProductDO::getBoxGauge, reqVO.getBoxGauge())
                .eqIfPresent(ProductDO::getVolume, reqVO.getVolume())
                .eqIfPresent(ProductDO::getGrossWeight, reqVO.getGrossWeight())
                .eqIfPresent(ProductDO::getNetWeight, reqVO.getNetWeight())
                .eqIfPresent(ProductDO::getShipped, reqVO.getShipped())
                .betweenIfPresent(ProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductDO::getId));
    }

    Page<ProductRespVO> getProductPage(Page<ProductRespVO> page, @Param("pageReqVO") ProductPageReqVO pageReqVO);

    ProductRespVO getProduct(String productId);

    /*用户端 获取产品列表*/
    IPage<ProductDescVO> pageApp(IPage<ProductDescVO> mPage,@Param("productReqVO") ProductReqVO productReqVO);

    /*用户端查看产品详情*/
    ProductDescVO getDesc(Long id);

    /*产品推荐*/
    IPage<ProductDescVO> recommend(IPage<ProductDescVO> mPage,@Param("productReqVO") ProductReqVO pageParam);

    String getIsInqury(@Param("productId") String productId,@Param("userId") Long userId);
}
