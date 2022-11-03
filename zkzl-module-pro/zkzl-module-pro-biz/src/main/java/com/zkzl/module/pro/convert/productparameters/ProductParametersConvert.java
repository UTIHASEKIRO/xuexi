package com.zkzl.module.pro.convert.productparameters;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.controller.admin.product.vo.ParametersVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.productparameters.vo.*;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;

/**
 * 产品参数 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductParametersConvert {

    ProductParametersConvert INSTANCE = Mappers.getMapper(ProductParametersConvert.class);

    ProductParametersDO convert(ProductParametersCreateReqVO bean);

    ProductParametersDO convert(ProductParametersUpdateReqVO bean);

    ProductParametersDO convert(ParametersVO bean);

    ProductParametersRespVO convert(ProductParametersDO bean);

    List<ProductParametersRespVO> convertList(List<ProductParametersDO> list);

    PageResult<ProductParametersRespVO> convertPage(PageResult<ProductParametersDO> page);

    List<ProductParametersExcelVO> convertList02(List<ProductParametersDO> list);

}
