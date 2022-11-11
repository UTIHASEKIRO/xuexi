package com.zkzl.module.pro.convert.company;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.company.vo.*;
import com.zkzl.module.pro.dal.dataobject.company.CompanyDO;

/**
 * 公司信息维护 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface CompanyConvert {

    CompanyConvert INSTANCE = Mappers.getMapper(CompanyConvert.class);

    CompanyDO convert(CompanyCreateReqVO bean);

    CompanyDO convert(CompanyUpdateReqVO bean);

    CompanyRespVO convert(CompanyDO bean);

    List<CompanyRespVO> convertList(List<CompanyDO> list);

    PageResult<CompanyRespVO> convertPage(PageResult<CompanyDO> page);

    List<CompanyExcelVO> convertList02(List<CompanyDO> list);

}
