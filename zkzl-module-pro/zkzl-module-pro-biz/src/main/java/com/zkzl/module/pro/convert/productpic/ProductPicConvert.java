package com.zkzl.module.pro.convert.productpic;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.productpic.vo.*;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;

/**
 * 产品图片 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductPicConvert {

    ProductPicConvert INSTANCE = Mappers.getMapper(ProductPicConvert.class);

    ProductPicDO convert(ProductPicCreateReqVO bean);

    ProductPicDO convert(ProductPicUpdateReqVO bean);

    ProductPicRespVO convert(ProductPicDO bean);

    List<ProductPicRespVO> convertList(List<ProductPicDO> list);

    PageResult<ProductPicRespVO> convertPage(PageResult<ProductPicDO> page);

    List<ProductPicExcelVO> convertList02(List<ProductPicDO> list);

}
