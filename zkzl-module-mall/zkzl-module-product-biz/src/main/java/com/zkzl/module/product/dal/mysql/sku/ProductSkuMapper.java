package com.zkzl.module.product.dal.mysql.sku;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.module.product.controller.admin.sku.vo.ProductSkuExportReqVO;
import com.zkzl.module.product.controller.admin.sku.vo.ProductSkuPageReqVO;
import com.zkzl.module.product.dal.dataobject.sku.ProductSkuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品sku Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductSkuMapper extends BaseMapperX<ProductSkuDO> {

    default PageResult<ProductSkuDO> selectPage(ProductSkuPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductSkuDO>()
                .eqIfPresent(ProductSkuDO::getSpuId, reqVO.getSpuId())
                .eqIfPresent(ProductSkuDO::getProperties, reqVO.getProperties())
                .eqIfPresent(ProductSkuDO::getPrice, reqVO.getPrice())
                .eqIfPresent(ProductSkuDO::getOriginalPrice, reqVO.getOriginalPrice())
                .eqIfPresent(ProductSkuDO::getCostPrice, reqVO.getCostPrice())
                .eqIfPresent(ProductSkuDO::getBarCode, reqVO.getBarCode())
                .eqIfPresent(ProductSkuDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(ProductSkuDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ProductSkuDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductSkuDO::getId));
    }

    default List<ProductSkuDO> selectList(ProductSkuExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductSkuDO>()
                .eqIfPresent(ProductSkuDO::getSpuId, reqVO.getSpuId())
                .eqIfPresent(ProductSkuDO::getProperties, reqVO.getProperties())
                .eqIfPresent(ProductSkuDO::getPrice, reqVO.getPrice())
                .eqIfPresent(ProductSkuDO::getOriginalPrice, reqVO.getOriginalPrice())
                .eqIfPresent(ProductSkuDO::getCostPrice, reqVO.getCostPrice())
                .eqIfPresent(ProductSkuDO::getBarCode, reqVO.getBarCode())
                .eqIfPresent(ProductSkuDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(ProductSkuDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ProductSkuDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductSkuDO::getId));
    }

    // TODO @franky：方法名 selectList; 可以直接调用 selectList
    default List<ProductSkuDO> selectBySpuIds(List<Long> spuIds) {
        return selectList(new LambdaQueryWrapperX<ProductSkuDO>()
                .inIfPresent(ProductSkuDO::getSpuId, spuIds));
    }

    default void deleteBySpuId(Long spuId) {
        // TODO @franky：直接 delete(new XXX) 即可，更简洁一些
        LambdaQueryWrapperX<ProductSkuDO> lambdaQueryWrapperX = new LambdaQueryWrapperX<ProductSkuDO>()
                .eqIfPresent(ProductSkuDO::getSpuId, spuId);
        delete(lambdaQueryWrapperX);
    }
}
