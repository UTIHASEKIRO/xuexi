package com.zkzl.module.pro.convert.priceinqurychild;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqurychild.PriceInquryChildDO;

/**
 * 询价表子 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface PriceInquryChildConvert {

    PriceInquryChildConvert INSTANCE = Mappers.getMapper(PriceInquryChildConvert.class);

    PriceInquryChildDO convert(PriceInquryChildCreateReqVO bean);

    PriceInquryChildDO convert(PriceInquryChildUpdateReqVO bean);

    PriceInquryChildRespVO convert(PriceInquryChildDO bean);

    List<PriceInquryChildRespVO> convertList(List<PriceInquryChildDO> list);

    PageResult<PriceInquryChildRespVO> convertPage(PageResult<PriceInquryChildDO> page);

    List<PriceInquryChildExcelVO> convertList02(List<PriceInquryChildDO> list);

}
