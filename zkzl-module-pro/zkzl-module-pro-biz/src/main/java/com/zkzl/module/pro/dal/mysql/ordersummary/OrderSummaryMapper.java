package com.zkzl.module.pro.dal.mysql.ordersummary;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.ordersummary.OrderSummaryDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.ordersummary.vo.*;

/**
 * 订单汇总 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderSummaryMapper extends BaseMapperX<OrderSummaryDO> {

    default PageResult<OrderSummaryDO> selectPage(OrderSummaryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderSummaryDO>()
                .eqIfPresent(OrderSummaryDO::getOrderSummaryId, reqVO.getOrderSummaryId())
                .eqIfPresent(OrderSummaryDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(OrderSummaryDO::getCustomer, reqVO.getCustomer())
                .eqIfPresent(OrderSummaryDO::getFinishPercent, reqVO.getFinishPercent())
                .eqIfPresent(OrderSummaryDO::getSalesman, reqVO.getSalesman())
                .eqIfPresent(OrderSummaryDO::getContractAmount, reqVO.getContractAmount())
                .eqIfPresent(OrderSummaryDO::getDepositAmount, reqVO.getDepositAmount())
                .eqIfPresent(OrderSummaryDO::getBalanceAmount, reqVO.getBalanceAmount())
                .betweenIfPresent(OrderSummaryDO::getProduceDate, reqVO.getProduceDate())
                .betweenIfPresent(OrderSummaryDO::getConfirmDate, reqVO.getConfirmDate())
                .betweenIfPresent(OrderSummaryDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(OrderSummaryDO::getResult, reqVO.getResult())
                .eqIfPresent(OrderSummaryDO::getVolume, reqVO.getVolume())
                .betweenIfPresent(OrderSummaryDO::getPackTime, reqVO.getPackTime())
                .betweenIfPresent(OrderSummaryDO::getDeleveryTime, reqVO.getDeleveryTime())
                .betweenIfPresent(OrderSummaryDO::getEndingTime, reqVO.getEndingTime())
                .eqIfPresent(OrderSummaryDO::getRemarks, reqVO.getRemarks())
                .orderByDesc(OrderSummaryDO::getId));
    }

    default List<OrderSummaryDO> selectList(OrderSummaryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderSummaryDO>()
                .eqIfPresent(OrderSummaryDO::getOrderSummaryId, reqVO.getOrderSummaryId())
                .eqIfPresent(OrderSummaryDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(OrderSummaryDO::getCustomer, reqVO.getCustomer())
                .eqIfPresent(OrderSummaryDO::getFinishPercent, reqVO.getFinishPercent())
                .eqIfPresent(OrderSummaryDO::getSalesman, reqVO.getSalesman())
                .eqIfPresent(OrderSummaryDO::getContractAmount, reqVO.getContractAmount())
                .eqIfPresent(OrderSummaryDO::getDepositAmount, reqVO.getDepositAmount())
                .eqIfPresent(OrderSummaryDO::getBalanceAmount, reqVO.getBalanceAmount())
                .betweenIfPresent(OrderSummaryDO::getProduceDate, reqVO.getProduceDate())
                .betweenIfPresent(OrderSummaryDO::getConfirmDate, reqVO.getConfirmDate())
                .betweenIfPresent(OrderSummaryDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(OrderSummaryDO::getResult, reqVO.getResult())
                .eqIfPresent(OrderSummaryDO::getVolume, reqVO.getVolume())
                .betweenIfPresent(OrderSummaryDO::getPackTime, reqVO.getPackTime())
                .betweenIfPresent(OrderSummaryDO::getDeleveryTime, reqVO.getDeleveryTime())
                .betweenIfPresent(OrderSummaryDO::getEndingTime, reqVO.getEndingTime())
                .eqIfPresent(OrderSummaryDO::getRemarks, reqVO.getRemarks())
                .orderByDesc(OrderSummaryDO::getId));
    }

}
