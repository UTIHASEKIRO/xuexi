package com.zkzl.module.pro.convert.priceinqury;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.priceinqury.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqury.PriceInquryDO;

/**
 * 询价 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface PriceInquryConvert {

    PriceInquryConvert INSTANCE = Mappers.getMapper(PriceInquryConvert.class);

    PriceInquryDO convert(PriceInquryCreateReqVO bean);

    PriceInquryDO convert(PriceInquryUpdateReqVO bean);

    PriceInquryRespVO convert(PriceInquryDO bean);

    List<PriceInquryRespVO> convertList(List<PriceInquryDO> list);

    PageResult<PriceInquryRespVO> convertPage(PageResult<PriceInquryDO> page);

    List<PriceInquryExcelVO> convertList02(List<PriceInquryDO> list);

}
