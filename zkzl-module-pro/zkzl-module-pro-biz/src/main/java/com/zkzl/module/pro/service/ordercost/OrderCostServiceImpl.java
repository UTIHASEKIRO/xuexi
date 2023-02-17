package com.zkzl.module.pro.service.ordercost;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.ordercost.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordercost.OrderCostDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.ordercost.OrderCostConvert;
import com.zkzl.module.pro.dal.mysql.ordercost.OrderCostMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.ORDER_COST_NOT_EXISTS;

/**
 * 订单成本 Service 实现类
 *
 * @author admin2
 */
@Service
@Validated
public class OrderCostServiceImpl implements OrderCostService {

    @Resource
    private OrderCostMapper orderCostMapper;

    @Override
    public Long createOrderCost(OrderCostCreateReqVO createReqVO) {
        // 插入
        OrderCostDO orderCost = OrderCostConvert.INSTANCE.convert(createReqVO);
        orderCostMapper.insert(orderCost);
        // 返回
        return orderCost.getId();
    }

    @Override
    public void updateOrderCost(OrderCostUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateOrderCostExists(updateReqVO.getId());
        // 更新
        OrderCostDO updateObj = OrderCostConvert.INSTANCE.convert(updateReqVO);
        orderCostMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderCost(Long id) {
        // 校验存在
        this.validateOrderCostExists(id);
        // 删除
        orderCostMapper.deleteById(id);
    }

    private void validateOrderCostExists(Long id) {
        if (orderCostMapper.selectById(id) == null) {
            throw exception(ORDER_COST_NOT_EXISTS);
        }
    }

    @Override
    public OrderCostDO getOrderCost(Long id) {
        return orderCostMapper.selectById(id);
    }

    @Override
    public List<OrderCostDO> getOrderCostList(Collection<Long> ids) {
        return orderCostMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderCostDO> getOrderCostPage(OrderCostPageReqVO pageReqVO) {
        return orderCostMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderCostDO> getOrderCostList(OrderCostExportReqVO exportReqVO) {
        return orderCostMapper.selectList(exportReqVO);
    }

}
