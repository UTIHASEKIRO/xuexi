package com.zkzl.module.pro.dal.mysql.ordercost;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.ordercost.OrderCostDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.ordercost.vo.*;

/**
 * 订单成本 Mapper
 *
 * @author admin2
 */
@Mapper
public interface OrderCostMapper extends BaseMapperX<OrderCostDO> {

    default PageResult<OrderCostDO> selectPage(OrderCostPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderCostDO>()
                .eqIfPresent(OrderCostDO::getOrderCostId, reqVO.getOrderCostId())
                .eq(StringUtils.isNotEmpty(reqVO.getProductId()),OrderCostDO::getProductId, reqVO.getProductId())
                .eqIfPresent(OrderCostDO::getUnitPrice, reqVO.getUnitPrice())
                .eqIfPresent(OrderCostDO::getMount, reqVO.getMount())
                .eqIfPresent(OrderCostDO::getSupplyInfoId, reqVO.getSupplyInfoId())
                .eqIfPresent(OrderCostDO::getCostPrice, reqVO.getCostPrice())
                .betweenIfPresent(OrderCostDO::getPriceDate, reqVO.getPriceDate())
                .betweenIfPresent(OrderCostDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(OrderCostDO::getEndTime, reqVO.getEndTime())
                .betweenIfPresent(OrderCostDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderCostDO::getId));
    }

    default List<OrderCostDO> selectList(OrderCostExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderCostDO>()
                .eqIfPresent(OrderCostDO::getOrderCostId, reqVO.getOrderCostId())
                .eqIfPresent(OrderCostDO::getProductId, reqVO.getProductId())
                .eqIfPresent(OrderCostDO::getUnitPrice, reqVO.getUnitPrice())
                .eqIfPresent(OrderCostDO::getMount, reqVO.getMount())
                .eqIfPresent(OrderCostDO::getSupplyInfoId, reqVO.getSupplyInfoId())
                .eqIfPresent(OrderCostDO::getCostPrice, reqVO.getCostPrice())
                .betweenIfPresent(OrderCostDO::getPriceDate, reqVO.getPriceDate())
                .betweenIfPresent(OrderCostDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(OrderCostDO::getEndTime, reqVO.getEndTime())
                .betweenIfPresent(OrderCostDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderCostDO::getId));
    }

}
