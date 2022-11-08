package com.zkzl.module.pro.service.ordersummary;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.ordersummary.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordersummary.OrderSummaryDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 订单汇总 Service 接口
 *
 * @author 芋道源码
 */
public interface OrderSummaryService {

    /**
     * 创建订单汇总
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderSummary(@Valid OrderSummaryCreateReqVO createReqVO);

    /**
     * 更新订单汇总
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderSummary(@Valid OrderSummaryUpdateReqVO updateReqVO);

    /**
     * 删除订单汇总
     *
     * @param id 编号
     */
    void deleteOrderSummary(Long id);

    /**
     * 获得订单汇总
     *
     * @param id 编号
     * @return 订单汇总
     */
    OrderSummaryDO getOrderSummary(Long id);

    /**
     * 获得订单汇总列表
     *
     * @param ids 编号
     * @return 订单汇总列表
     */
    List<OrderSummaryDO> getOrderSummaryList(Collection<Long> ids);

    /**
     * 获得订单汇总分页
     *
     * @param pageReqVO 分页查询
     * @return 订单汇总分页
     */
    PageResult<OrderSummaryDO> getOrderSummaryPage(OrderSummaryPageReqVO pageReqVO);

    /**
     * 获得订单汇总列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 订单汇总列表
     */
    List<OrderSummaryDO> getOrderSummaryList(OrderSummaryExportReqVO exportReqVO);

    /*
    订单汇总分页
     */
    PageResult<OrderSummaryPageVO> pageOrderSummary(OrderSummaryPageVO pageVO);

    /*获得订单总数、完成数、正在进行数*/
    Map<String, Long> pageOrderCount();
}
