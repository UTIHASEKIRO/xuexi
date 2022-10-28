package com.zkzl.module.pro.dal.mysql.product;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.product.vo.*;

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
                .likeIfPresent(ProductDO::getProductName, reqVO.getProductName())
                .betweenIfPresent(ProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductDO::getId));
    }

    default List<ProductDO> selectList(ProductExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductDO>()
                .eqIfPresent(ProductDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductDO::getTypeId, reqVO.getTypeId())
                .likeIfPresent(ProductDO::getProductName, reqVO.getProductName())
                .betweenIfPresent(ProductDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductDO::getId));
    }

}
