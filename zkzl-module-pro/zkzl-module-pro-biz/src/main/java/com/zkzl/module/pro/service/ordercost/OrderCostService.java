package com.zkzl.module.pro.service.ordercost;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.ordercost.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordercost.OrderCostDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 订单成本 Service 接口
 *
 * @author admin2
 */
public interface OrderCostService {

    /**
     * 创建订单成本
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderCost(@Valid OrderCostCreateReqVO createReqVO);

    /**
     * 更新订单成本
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderCost(@Valid OrderCostUpdateReqVO updateReqVO);

    /**
     * 删除订单成本
     *
     * @param id 编号
     */
    void deleteOrderCost(Long id);

    /**
     * 获得订单成本
     *
     * @param id 编号
     * @return 订单成本
     */
    OrderCostDO getOrderCost(Long id);

    /**
     * 获得订单成本列表
     *
     * @param ids 编号
     * @return 订单成本列表
     */
    List<OrderCostDO> getOrderCostList(Collection<Long> ids);

    /**
     * 获得订单成本分页
     *
     * @param pageReqVO 分页查询
     * @return 订单成本分页
     */
    PageResult<OrderCostDO> getOrderCostPage(OrderCostPageReqVO pageReqVO);

    /**
     * 获得订单成本列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 订单成本列表
     */
    List<OrderCostDO> getOrderCostList(OrderCostExportReqVO exportReqVO);

}
