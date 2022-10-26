package com.zkzl.module.product.convert.brand;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.product.controller.admin.brand.vo.*;
import com.zkzl.module.product.dal.dataobject.brand.BrandDO;

/**
 * 品牌 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface BrandConvert {

    BrandConvert INSTANCE = Mappers.getMapper(BrandConvert.class);

    BrandDO convert(BrandCreateReqVO bean);

    BrandDO convert(BrandUpdateReqVO bean);

    BrandRespVO convert(BrandDO bean);

    List<BrandRespVO> convertList(List<BrandDO> list);

    PageResult<BrandRespVO> convertPage(PageResult<BrandDO> page);

    List<BrandExcelVO> convertList02(List<BrandDO> list);

}
