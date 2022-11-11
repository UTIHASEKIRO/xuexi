package com.zkzl.module.pro.convert.partners;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.partners.vo.*;
import com.zkzl.module.pro.dal.dataobject.partners.PartnersDO;

/**
 * 合作伙伴 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface PartnersConvert {

    PartnersConvert INSTANCE = Mappers.getMapper(PartnersConvert.class);

    PartnersDO convert(PartnersCreateReqVO bean);

    PartnersDO convert(PartnersUpdateReqVO bean);

    PartnersRespVO convert(PartnersDO bean);

    List<PartnersRespVO> convertList(List<PartnersDO> list);

    PageResult<PartnersRespVO> convertPage(PageResult<PartnersDO> page);

    List<PartnersExcelVO> convertList02(List<PartnersDO> list);

}
