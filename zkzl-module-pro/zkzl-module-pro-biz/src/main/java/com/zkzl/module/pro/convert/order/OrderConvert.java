package com.zkzl.module.pro.convert.order;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.order.vo.*;
import com.zkzl.module.pro.dal.dataobject.order.OrderDO;

/**
 * 订单 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderConvert {

    OrderConvert INSTANCE = Mappers.getMapper(OrderConvert.class);

    OrderDO convert(OrderCreateReqVO bean);

    OrderDO convert(OrderUpdateReqVO bean);

    OrderRespVO convert(OrderDO bean);

    List<OrderRespVO> convertList(List<OrderDO> list);

    PageResult<OrderRespVO> convertPage(PageResult<OrderDO> page);

    List<OrderExcelVO> convertList02(List<OrderDO> list);

}
