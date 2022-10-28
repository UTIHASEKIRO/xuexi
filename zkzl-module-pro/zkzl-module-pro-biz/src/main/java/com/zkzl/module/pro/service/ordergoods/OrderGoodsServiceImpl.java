package com.zkzl.module.pro.service.ordergoods;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.ordergoods.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordergoods.OrderGoodsDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.ordergoods.OrderGoodsConvert;
import com.zkzl.module.pro.dal.mysql.ordergoods.OrderGoodsMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.ORDER_GOODS_NOT_EXISTS;


/**
 * 订单商品关联表	 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OrderGoodsServiceImpl implements OrderGoodsService {

    @Resource
    private OrderGoodsMapper orderGoodsMapper;

    @Override
    public Long createOrderGoods(OrderGoodsCreateReqVO createReqVO) {
        // 插入
        OrderGoodsDO orderGoods = OrderGoodsConvert.INSTANCE.convert(createReqVO);
        orderGoodsMapper.insert(orderGoods);
        // 返回
        return orderGoods.getId();
    }

    @Override
    public void updateOrderGoods(OrderGoodsUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateOrderGoodsExists(updateReqVO.getId());
        // 更新
        OrderGoodsDO updateObj = OrderGoodsConvert.INSTANCE.convert(updateReqVO);
        orderGoodsMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrderGoods(Long id) {
        // 校验存在
        this.validateOrderGoodsExists(id);
        // 删除
        orderGoodsMapper.deleteById(id);
    }

    private void validateOrderGoodsExists(Long id) {
        if (orderGoodsMapper.selectById(id) == null) {
            throw exception(ORDER_GOODS_NOT_EXISTS);
        }
    }

    @Override
    public OrderGoodsDO getOrderGoods(Long id) {
        return orderGoodsMapper.selectById(id);
    }

    @Override
    public List<OrderGoodsDO> getOrderGoodsList(Collection<Long> ids) {
        return orderGoodsMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderGoodsDO> getOrderGoodsPage(OrderGoodsPageReqVO pageReqVO) {
        return orderGoodsMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OrderGoodsDO> getOrderGoodsList(OrderGoodsExportReqVO exportReqVO) {
        return orderGoodsMapper.selectList(exportReqVO);
    }

}
