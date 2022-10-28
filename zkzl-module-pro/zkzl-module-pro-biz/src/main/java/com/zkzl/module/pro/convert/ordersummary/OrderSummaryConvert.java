package com.zkzl.module.pro.convert.ordersummary;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.ordersummary.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordersummary.OrderSummaryDO;

/**
 * 订单汇总 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderSummaryConvert {

    OrderSummaryConvert INSTANCE = Mappers.getMapper(OrderSummaryConvert.class);

    OrderSummaryDO convert(OrderSummaryCreateReqVO bean);

    OrderSummaryDO convert(OrderSummaryUpdateReqVO bean);

    OrderSummaryRespVO convert(OrderSummaryDO bean);

    List<OrderSummaryRespVO> convertList(List<OrderSummaryDO> list);

    PageResult<OrderSummaryRespVO> convertPage(PageResult<OrderSummaryDO> page);

    List<OrderSummaryExcelVO> convertList02(List<OrderSummaryDO> list);

}
