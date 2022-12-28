package com.zkzl.module.pro.service.order;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import com.zkzl.module.pro.controller.admin.priceinqury.vo.PriceInquryBaseVO;
import com.zkzl.module.pro.controller.admin.supplyinfo.vo.SupplyInfoNameVO;
import com.zkzl.module.pro.controller.app.order.vo.OrderDescVO;
import com.zkzl.module.pro.dal.dataobject.priceinqury.PriceInquryDO;
import com.zkzl.module.pro.dal.mysql.order.ProOrderMapper;
import com.zkzl.module.pro.dal.mysql.priceinqury.PriceInquryMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.order.vo.*;
import com.zkzl.module.pro.dal.dataobject.order.OrderDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.order.OrderConvert;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.ORDER_NOT_EXISTS;

/**
 * 订单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OrderServiceImpl implements OrderService {

    @Resource
    private ProOrderMapper orderMapper;

    @Resource
    private PriceInquryMapper priceInquryMapper;

    @Override
    public Long createOrder(OrderCreateReqVO createReqVO) {
        // 插入
        OrderDO order = OrderConvert.INSTANCE.convert(createReqVO);
        order.setOrderId(IdUtil.getSnowflakeNextIdStr());
        orderMapper.insert(order);
        // 返回
        return order.getId();
    }

    @Override
    public void updateOrder(OrderUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateOrderExists(updateReqVO.getId());
        // 更新
        OrderDO updateObj = OrderConvert.INSTANCE.convert(updateReqVO);
        orderMapper.updateById(updateObj);
    }

    @Override
    public void deleteOrder(Long id) {
        // 校验存在
        this.validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public OrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<OrderDO> getOrderList(Collection<Long> ids) {
        return orderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<OrderDO> getOrderPage(OrderPageReqVO pageReqVO) {
        IPage<OrderDO> mPage = MyBatisUtils.buildPage(pageReqVO);
        orderMapper.getPage(mPage,pageReqVO);
        return new PageResult<>(mPage.getRecords(), mPage.getTotal());
    }

    @Override
    public PageResult<OrderDO> appGetOrderPage(OrderPageReqVO pageReqVO) {
        PageResult<OrderDO> result = orderMapper.selectPage(pageReqVO);
        List<OrderDO> list = result.getList();
        for (OrderDO orderDO : list) {
            PriceInquryDO price = priceInquryMapper.selectOne("price_inqury_id",orderDO.getPriceInquryId());
            orderDO.setRemarks(price.getBuyerIdealPrice());//买方理想价格
            orderDO.setBuyerCompanyName(price.getBuyerCompanyName());
            orderDO.setBuyerCompanyAddress(price.getBuyerCompanyAddress());
            orderDO.setBuyerContact(price.getBuyerContact());
            orderDO.setBuyerTel(price.getBuyerTel());

        }
        return result;
    }

    @Override
    public List<OrderDO> getOrderList(OrderExportReqVO exportReqVO) {
        return orderMapper.selectList(exportReqVO);
    }

    @Override
    public OrderRespVO getOrderByOrderId(String orderId) {
        return orderMapper.getOrderByOrderId(orderId);
    }

    @Override
    public OrderDescVO orderDesc(OrderPageReqVO param) {
        return orderMapper.orderDesc(param.getOrderId());
    }

}
