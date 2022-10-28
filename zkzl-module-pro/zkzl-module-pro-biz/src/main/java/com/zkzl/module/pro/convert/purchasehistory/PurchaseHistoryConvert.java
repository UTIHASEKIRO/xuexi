package com.zkzl.module.pro.convert.purchasehistory;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.purchasehistory.vo.*;
import com.zkzl.module.pro.dal.dataobject.purchasehistory.PurchaseHistoryDO;

/**
 * 供货商供应记录 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface PurchaseHistoryConvert {

    PurchaseHistoryConvert INSTANCE = Mappers.getMapper(PurchaseHistoryConvert.class);

    PurchaseHistoryDO convert(PurchaseHistoryCreateReqVO bean);

    PurchaseHistoryDO convert(PurchaseHistoryUpdateReqVO bean);

    PurchaseHistoryRespVO convert(PurchaseHistoryDO bean);

    List<PurchaseHistoryRespVO> convertList(List<PurchaseHistoryDO> list);

    PageResult<PurchaseHistoryRespVO> convertPage(PageResult<PurchaseHistoryDO> page);

    List<PurchaseHistoryExcelVO> convertList02(List<PurchaseHistoryDO> list);

}
