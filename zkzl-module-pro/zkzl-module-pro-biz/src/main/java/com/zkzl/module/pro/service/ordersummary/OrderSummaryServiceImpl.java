package com.zkzl.module.pro.service.ordersummary;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.zkzl.module.pro.controller.admin.ordersummary.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordersummary.OrderSummaryDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.ordersummary.OrderSummaryConvert;
import com.zkzl.module.pro.dal.mysql.ordersummary.OrderSummaryMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.ORDER_SUMMARY_NOT_EXISTS;

/**
 * 订单汇总 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OrderSummaryServiceImpl implements OrderSummaryService {

    @Resource
    private OrderSummaryMapper orderSummaryMapper;

    @Override
    public Long createOrderSummary(OrderSummaryCreateReqVO createReqVO) {
        // 插入
        OrderSummaryDO orderSummary = OrderSummaryConvert.INSTANCE.convert(createReqVO);
        orderSummaryMapper.insert(orderSummary);
        // 返回
        return orderSummary.getId();
    }

    @Override
    public void updateOrderSummary(OrderSummaryUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateOrderSummaryExists(updateReqVO.getId());
        // 更新
        OrderSummaryDO updateObj = OrderSummaryConvert.INSTANCE.convert(updateReqVO);
        orderSummaryMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderSummary(Long id) {
        // 校验存在
        this.validateOrderSummaryExists(id);
        // 删除
        orderSummaryMapper.deleteById(id);
    }

    private void validateOrderSummaryExists(Long id) {
        if (orderSummaryMapper.selectById(id) == null) {
            throw exception(ORDER_SUMMARY_NOT_EXISTS);
        }
    }

    @Override
    public OrderSummaryDO getOrderSummary(Long id) {
        return orderSummaryMapper.selectById(id);
    }

    @Override
    public List<OrderSummaryDO> getOrderSummaryList(Collection<Long> ids) {
        return orderSummaryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderSummaryDO> getOrderSummaryPage(OrderSummaryPageReqVO pageReqVO) {
        return orderSummaryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderSummaryDO> getOrderSummaryList(OrderSummaryExportReqVO exportReqVO) {
        return orderSummaryMapper.selectList(exportReqVO);
    }

    @Override
    public PageResult<OrderSummaryPageVO> pageOrderSummary(OrderSummaryPageVO pageVO) {
        IPage<OrderSummaryPageVO> mPage = MyBatisUtils.buildPage(pageVO);
        orderSummaryMapper.pageOrderSummary(mPage,pageVO);
        return new PageResult<>(mPage.getRecords(),mPage.getTotal());
    }

    @Override
    public Map<String, Long> pageOrderCount() {
        /*获得订单总数、完成数、正在进行数*/
        Map<String,Long> result = new ConcurrentHashMap<>();
        result.put("订单总数",orderSummaryMapper.selectCount());
        result.put("已经完成",orderSummaryMapper.selectCount("status","6"));
        result.put("正在进行",orderSummaryMapper.selectCount(new LambdaQueryWrapperX<OrderSummaryDO>().ne(OrderSummaryDO::getStatus,"6")));
        return result;
    }

}
