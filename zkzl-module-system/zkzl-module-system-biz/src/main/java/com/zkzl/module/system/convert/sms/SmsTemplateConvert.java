package com.zkzl.module.system.convert.sms;

import com.zkzl.module.system.controller.admin.sms.vo.template.SmsTemplateCreateReqVO;
import com.zkzl.module.system.controller.admin.sms.vo.template.SmsTemplateExcelVO;
import com.zkzl.module.system.controller.admin.sms.vo.template.SmsTemplateRespVO;
import com.zkzl.module.system.controller.admin.sms.vo.template.SmsTemplateUpdateReqVO;
import com.zkzl.module.system.dal.dataobject.sms.SmsTemplateDO;
import com.zkzl.framework.common.pojo.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SmsTemplateConvert {

    SmsTemplateConvert INSTANCE = Mappers.getMapper(SmsTemplateConvert.class);

    SmsTemplateDO convert(SmsTemplateCreateReqVO bean);

    SmsTemplateDO convert(SmsTemplateUpdateReqVO bean);

    SmsTemplateRespVO convert(SmsTemplateDO bean);

    List<SmsTemplateRespVO> convertList(List<SmsTemplateDO> list);

    PageResult<SmsTemplateRespVO> convertPage(PageResult<SmsTemplateDO> page);

    List<SmsTemplateExcelVO> convertList02(List<SmsTemplateDO> list);

}
