package com.zkzl.module.pro.convert.producttype;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.producttype.vo.*;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;

/**
 * 商品类别 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductTypeConvert {

    ProductTypeConvert INSTANCE = Mappers.getMapper(ProductTypeConvert.class);

    ProductTypeDO convert(ProductTypeCreateReqVO bean);

    ProductTypeDO convert(ProductTypeUpdateReqVO bean);

    ProductTypeRespVO convert(ProductTypeDO bean);

    List<ProductTypeRespVO> convertList(List<ProductTypeDO> list);

    PageResult<ProductTypeRespVO> convertPage(PageResult<ProductTypeDO> page);

    List<ProductTypeExcelVO> convertList02(List<ProductTypeDO> list);

}
