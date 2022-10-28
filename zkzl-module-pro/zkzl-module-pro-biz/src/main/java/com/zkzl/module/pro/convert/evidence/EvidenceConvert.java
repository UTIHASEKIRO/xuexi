package com.zkzl.module.pro.convert.evidence;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.evidence.vo.*;
import com.zkzl.module.pro.dal.dataobject.evidence.EvidenceDO;

/**
 * 订单凭证信息 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface EvidenceConvert {

    EvidenceConvert INSTANCE = Mappers.getMapper(EvidenceConvert.class);

    EvidenceDO convert(EvidenceCreateReqVO bean);

    EvidenceDO convert(EvidenceUpdateReqVO bean);

    EvidenceRespVO convert(EvidenceDO bean);

    List<EvidenceRespVO> convertList(List<EvidenceDO> list);

    PageResult<EvidenceRespVO> convertPage(PageResult<EvidenceDO> page);

    List<EvidenceExcelVO> convertList02(List<EvidenceDO> list);

}
