package com.zkzl.module.test.convert.generate;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.test.controller.admin.generate.vo.*;
import com.zkzl.module.test.dal.dataobject.generate.GenerateDO;

/**
 * 测试自动代码生成 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface GenerateConvert {

    GenerateConvert INSTANCE = Mappers.getMapper(GenerateConvert.class);

    @Mapping(source = "con", target = "content")  //前面指接收类，后面指向数据库内字段名称
    GenerateDO convert(GenerateCreateReqVO bean);

    GenerateDO convert(GenerateUpdateReqVO bean);

    GenerateRespVO convert(GenerateDO bean);

    List<GenerateRespVO> convertList(List<GenerateDO> list);

    PageResult<GenerateRespVO> convertPage(PageResult<GenerateDO> page);

    List<GenerateExcelVO> convertList02(List<GenerateDO> list);

}
