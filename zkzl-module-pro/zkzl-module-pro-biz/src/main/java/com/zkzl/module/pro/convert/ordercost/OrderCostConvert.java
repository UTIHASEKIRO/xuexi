package com.zkzl.module.pro.convert.ordercost;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.ordercost.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordercost.OrderCostDO;

/**
 * 订单成本 Convert
 *
 * @author admin2
 */
@Mapper
public interface OrderCostConvert {

    OrderCostConvert INSTANCE = Mappers.getMapper(OrderCostConvert.class);

    OrderCostDO convert(OrderCostCreateReqVO bean);

    OrderCostDO convert(OrderCostUpdateReqVO bean);

    OrderCostRespVO convert(OrderCostDO bean);

    List<OrderCostRespVO> convertList(List<OrderCostDO> list);

    PageResult<OrderCostRespVO> convertPage(PageResult<OrderCostDO> page);

    List<OrderCostExcelVO> convertList02(List<OrderCostDO> list);

}
