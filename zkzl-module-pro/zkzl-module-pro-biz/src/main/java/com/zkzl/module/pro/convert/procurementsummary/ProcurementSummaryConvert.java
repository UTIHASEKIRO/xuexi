package com.zkzl.module.pro.convert.procurementsummary;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.procurementsummary.vo.*;
import com.zkzl.module.pro.dal.dataobject.procurementsummary.ProcurementSummaryDO;

/**
 * 采购汇总 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProcurementSummaryConvert {

    ProcurementSummaryConvert INSTANCE = Mappers.getMapper(ProcurementSummaryConvert.class);

    ProcurementSummaryDO convert(ProcurementSummaryCreateReqVO bean);

    ProcurementSummaryDO convert(ProcurementSummaryUpdateReqVO bean);

    ProcurementSummaryRespVO convert(ProcurementSummaryDO bean);

    List<ProcurementSummaryRespVO> convertList(List<ProcurementSummaryDO> list);

    PageResult<ProcurementSummaryRespVO> convertPage(PageResult<ProcurementSummaryDO> page);

    List<ProcurementSummaryExcelVO> convertList02(List<ProcurementSummaryDO> list);

}
