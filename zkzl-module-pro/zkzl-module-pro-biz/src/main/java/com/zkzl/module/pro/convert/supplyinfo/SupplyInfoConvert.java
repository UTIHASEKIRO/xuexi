package com.zkzl.module.pro.convert.supplyinfo;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.supplyinfo.vo.*;
import com.zkzl.module.pro.dal.dataobject.supplyinfo.SupplyInfoDO;

/**
 * 供货商信息 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface SupplyInfoConvert {

    SupplyInfoConvert INSTANCE = Mappers.getMapper(SupplyInfoConvert.class);

    SupplyInfoDO convert(SupplyInfoCreateReqVO bean);

    SupplyInfoDO convert(SupplyInfoUpdateReqVO bean);

    SupplyInfoRespVO convert(SupplyInfoDO bean);

    List<SupplyInfoRespVO> convertList(List<SupplyInfoDO> list);

    PageResult<SupplyInfoRespVO> convertPage(PageResult<SupplyInfoDO> page);

    List<SupplyInfoExcelVO> convertList02(List<SupplyInfoDO> list);

}
