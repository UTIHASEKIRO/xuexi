package com.zkzl.module.pro.convert.productfinalparametersname;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.productfinalparametersname.vo.*;
import com.zkzl.module.pro.dal.dataobject.productfinalparametersname.ProductFinalParametersNameDO;

/**
 * 产品固定参数名 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductFinalParametersNameConvert {

    ProductFinalParametersNameConvert INSTANCE = Mappers.getMapper(ProductFinalParametersNameConvert.class);

    ProductFinalParametersNameDO convert(ProductFinalParametersNameCreateReqVO bean);

    ProductFinalParametersNameDO convert(ProductFinalParametersNameUpdateReqVO bean);

    ProductFinalParametersNameRespVO convert(ProductFinalParametersNameDO bean);

    List<ProductFinalParametersNameRespVO> convertList(List<ProductFinalParametersNameDO> list);

    PageResult<ProductFinalParametersNameRespVO> convertPage(PageResult<ProductFinalParametersNameDO> page);

    List<ProductFinalParametersNameExcelVO> convertList02(List<ProductFinalParametersNameDO> list);

}
