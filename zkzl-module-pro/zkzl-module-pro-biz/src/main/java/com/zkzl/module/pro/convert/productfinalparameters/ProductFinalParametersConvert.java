package com.zkzl.module.pro.convert.productfinalparameters;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.productfinalparameters.vo.*;
import com.zkzl.module.pro.dal.dataobject.productfinalparameters.ProductFinalParametersDO;

/**
 * 产品固定参数 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductFinalParametersConvert {

    ProductFinalParametersConvert INSTANCE = Mappers.getMapper(ProductFinalParametersConvert.class);

    ProductFinalParametersDO convert(ProductFinalParametersCreateReqVO bean);

    ProductFinalParametersDO convert(ProductFinalParametersUpdateReqVO bean);

    ProductFinalParametersRespVO convert(ProductFinalParametersDO bean);

    List<ProductFinalParametersRespVO> convertList(List<ProductFinalParametersDO> list);

    PageResult<ProductFinalParametersRespVO> convertPage(PageResult<ProductFinalParametersDO> page);

    List<ProductFinalParametersExcelVO> convertList02(List<ProductFinalParametersDO> list);

}
