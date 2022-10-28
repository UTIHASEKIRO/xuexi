package com.zkzl.module.pro.dal.mysql.producttype;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.producttype.vo.*;

/**
 * 商品类别 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductTypeMapper extends BaseMapperX<ProductTypeDO> {

    default PageResult<ProductTypeDO> selectPage(ProductTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductTypeDO>()
                .eqIfPresent(ProductTypeDO::getParentTypeId, reqVO.getParentTypeId())
                .eqIfPresent(ProductTypeDO::getTypeId, reqVO.getTypeId())
                .likeIfPresent(ProductTypeDO::getTypeName, reqVO.getTypeName())
                .eqIfPresent(ProductTypeDO::getSort, reqVO.getSort())
                .betweenIfPresent(ProductTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductTypeDO::getId));
    }

    default List<ProductTypeDO> selectList(ProductTypeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductTypeDO>()
                .eqIfPresent(ProductTypeDO::getParentTypeId, reqVO.getParentTypeId())
                .eqIfPresent(ProductTypeDO::getTypeId, reqVO.getTypeId())
                .likeIfPresent(ProductTypeDO::getTypeName, reqVO.getTypeName())
                .eqIfPresent(ProductTypeDO::getSort, reqVO.getSort())
                .betweenIfPresent(ProductTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProductTypeDO::getId));
    }

}
