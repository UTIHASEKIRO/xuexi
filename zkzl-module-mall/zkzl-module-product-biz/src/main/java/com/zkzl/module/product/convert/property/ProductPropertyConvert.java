package com.zkzl.module.product.convert.property;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.product.dal.dataobject.property.ProductPropertyDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.product.controller.admin.property.vo.*;

/**
 * 规格名称 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductPropertyConvert {

    ProductPropertyConvert INSTANCE = Mappers.getMapper(ProductPropertyConvert.class);

    ProductPropertyDO convert(ProductPropertyCreateReqVO bean);

    ProductPropertyDO convert(ProductPropertyUpdateReqVO bean);

    ProductPropertyRespVO convert(ProductPropertyDO bean);

    List<ProductPropertyRespVO> convertList(List<ProductPropertyDO> list);

    PageResult<ProductPropertyRespVO> convertPage(PageResult<ProductPropertyDO> page);

    List<ProductPropertyExcelVO> convertList02(List<ProductPropertyDO> list);

}
