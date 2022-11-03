package com.zkzl.module.pro.dal.mysql.productparameters;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.productparameters.vo.*;

/**
 * 产品参数 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductParametersMapper extends BaseMapperX<ProductParametersDO> {

    default PageResult<ProductParametersDO> selectPage(ProductParametersPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductParametersDO>()
                .eqIfPresent(ProductParametersDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductParametersDO::getParentParamId, reqVO.getParentParamId())
                .orderByDesc(ProductParametersDO::getId));
    }

    default List<ProductParametersDO> selectList(ProductParametersExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductParametersDO>()
                .eqIfPresent(ProductParametersDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductParametersDO::getParentParamId, reqVO.getParentParamId())
                .orderByDesc(ProductParametersDO::getId));
    }

}
