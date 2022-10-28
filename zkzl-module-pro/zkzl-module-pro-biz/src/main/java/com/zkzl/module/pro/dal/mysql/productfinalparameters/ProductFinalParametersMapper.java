package com.zkzl.module.pro.dal.mysql.productfinalparameters;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.productfinalparameters.ProductFinalParametersDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.productfinalparameters.vo.*;

/**
 * 产品固定参数 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductFinalParametersMapper extends BaseMapperX<ProductFinalParametersDO> {

    default PageResult<ProductFinalParametersDO> selectPage(ProductFinalParametersPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductFinalParametersDO>()
                .eqIfPresent(ProductFinalParametersDO::getFinalParametersId, reqVO.getFinalParametersId())
                .eqIfPresent(ProductFinalParametersDO::getParameterCn, reqVO.getParameterCn())
                .eqIfPresent(ProductFinalParametersDO::getParameterEn, reqVO.getParameterEn())
                .eqIfPresent(ProductFinalParametersDO::getValueCn, reqVO.getValueCn())
                .eqIfPresent(ProductFinalParametersDO::getValueEn, reqVO.getValueEn())
                .orderByDesc(ProductFinalParametersDO::getId));
    }

    default List<ProductFinalParametersDO> selectList(ProductFinalParametersExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductFinalParametersDO>()
                .eqIfPresent(ProductFinalParametersDO::getFinalParametersId, reqVO.getFinalParametersId())
                .eqIfPresent(ProductFinalParametersDO::getParameterCn, reqVO.getParameterCn())
                .eqIfPresent(ProductFinalParametersDO::getParameterEn, reqVO.getParameterEn())
                .eqIfPresent(ProductFinalParametersDO::getValueCn, reqVO.getValueCn())
                .eqIfPresent(ProductFinalParametersDO::getValueEn, reqVO.getValueEn())
                .orderByDesc(ProductFinalParametersDO::getId));
    }

}
