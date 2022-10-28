package com.zkzl.module.pro.service.ordergoods;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.ordergoods.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordergoods.OrderGoodsDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 订单商品关联表	 Service 接口
 *
 * @author 芋道源码
 */
public interface OrderGoodsService {

    /**
     * 创建订单商品关联表	
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderGoods(@Valid OrderGoodsCreateReqVO createReqVO);

    /**
     * 更新订单商品关联表	
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderGoods(@Valid OrderGoodsUpdateReqVO updateReqVO);

    /**
     * 删除订单商品关联表	
     *
     * @param id 编号
     */
    void deleteOrderGoods(Long id);

    /**
     * 获得订单商品关联表	
     *
     * @param id 编号
     * @return 订单商品关联表	
     */
    OrderGoodsDO getOrderGoods(Long id);

    /**
     * 获得订单商品关联表	列表
     *
     * @param ids 编号
     * @return 订单商品关联表	列表
     */
    List<OrderGoodsDO> getOrderGoodsList(Collection<Long> ids);

    /**
     * 获得订单商品关联表	分页
     *
     * @param pageReqVO 分页查询
     * @return 订单商品关联表	分页
     */
    PageResult<OrderGoodsDO> getOrderGoodsPage(OrderGoodsPageReqVO pageReqVO);

    /**
     * 获得订单商品关联表	列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 订单商品关联表	列表
     */
    List<OrderGoodsDO> getOrderGoodsList(OrderGoodsExportReqVO exportReqVO);

}
