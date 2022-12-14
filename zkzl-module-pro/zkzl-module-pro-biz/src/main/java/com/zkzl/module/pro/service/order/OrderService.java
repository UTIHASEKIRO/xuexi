package com.zkzl.module.pro.service.order;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.order.vo.*;
import com.zkzl.module.pro.controller.app.order.vo.OrderDescVO;
import com.zkzl.module.pro.dal.dataobject.order.OrderDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 订单 Service 接口
 *
 * @author 芋道源码
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid OrderCreateReqVO createReqVO);

    /**
     * 更新订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid OrderUpdateReqVO updateReqVO);

    /**
     * 删除订单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 获得订单
     *
     * @param id 编号
     * @return 订单
     */
    OrderDO getOrder(Long id);

    /**
     * 获得订单列表
     *
     * @param ids 编号
     * @return 订单列表
     */
    List<OrderDO> getOrderList(Collection<Long> ids);

    /**
     * 获得订单分页
     *
     * @param pageReqVO 分页查询
     * @return 订单分页
     */
    PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO);

    /**
     * 获得订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 订单列表
     */
    List<OrderDO> getOrderList(OrderExportReqVO exportReqVO);

    OrderRespVO getOrderByOrderId(String orderId);

    /*订单详情*/
    OrderDescVO orderDesc(OrderPageReqVO param);
}
