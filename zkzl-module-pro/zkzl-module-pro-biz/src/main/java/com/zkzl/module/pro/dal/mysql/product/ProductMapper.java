package com.zkzl.module.pro.dal.mysql.product;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.product.vo.*;
import org.apache.ibatis.annotations.Param;

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
                .betweenIfPresent(ProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductDO::getId));
    }

    default List<ProductDO> selectList(ProductExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductDO>()
                .eqIfPresent(ProductDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductDO::getTypeId, reqVO.getTypeId())
                .betweenIfPresent(ProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductDO::getId));
    }

    Page<ProductRespVO> getProductPage(Page<ProductRespVO> page, @Param("pageReqVO") ProductPageReqVO pageReqVO);

    ProductRespVO getProduct(String productId);
}
