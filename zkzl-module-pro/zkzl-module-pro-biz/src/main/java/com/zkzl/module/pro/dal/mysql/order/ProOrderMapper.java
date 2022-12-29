package com.zkzl.module.pro.dal.mysql.order;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.controller.app.order.vo.OrderDescVO;
import com.zkzl.module.pro.dal.dataobject.order.OrderDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.order.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 订单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProOrderMapper extends BaseMapperX<OrderDO> {

    default PageResult<OrderDO> selectPage(OrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getOrderId, reqVO.getOrderId())
                .likeIfPresent(OrderDO::getSellerCompanyName, reqVO.getSellerCompanyName())
                .eqIfPresent(OrderDO::getSellerCompanyAddress, reqVO.getSellerCompanyAddress())
                .eqIfPresent(OrderDO::getSellerContact, reqVO.getSellerContact())
                .eqIfPresent(OrderDO::getSellerTel, reqVO.getSellerTel())
                .eqIfPresent(OrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OrderDO::getPrice, reqVO.getPrice())
                .eqIfPresent(OrderDO::getDiscount, reqVO.getDiscount())
                .eqIfPresent(OrderDO::getTotal, reqVO.getTotal())
                .eqIfPresent(OrderDO::getRemarks, reqVO.getRemarks())
                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
                .orderByDesc(OrderDO::getId));
    }

    default List<OrderDO> selectList(OrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getOrderId, reqVO.getOrderId())
                .likeIfPresent(OrderDO::getSellerCompanyName, reqVO.getSellerCompanyName())
                .eqIfPresent(OrderDO::getSellerCompanyAddress, reqVO.getSellerCompanyAddress())
                .eqIfPresent(OrderDO::getSellerContact, reqVO.getSellerContact())
                .eqIfPresent(OrderDO::getSellerTel, reqVO.getSellerTel())
                .eqIfPresent(OrderDO::getUserId, reqVO.getUserId())
                .eqIfPresent(OrderDO::getPrice, reqVO.getPrice())
                .eqIfPresent(OrderDO::getDiscount, reqVO.getDiscount())
                .eqIfPresent(OrderDO::getTotal, reqVO.getTotal())
                .eqIfPresent(OrderDO::getRemarks, reqVO.getRemarks())
                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
                .orderByDesc(OrderDO::getId));
    }

    OrderRespVO getOrderByOrderId(String orderId);

    /*用户端-订单详情*/
    OrderDescVO orderDesc(String orderId);

    IPage<OrderDO> getPage(@Param("page")IPage<OrderDO> page, @Param("req") OrderPageReqVO pageReqVO);

    IPage<OrderDO> appGetOrderPage(@Param("mpPage") IPage<OrderDO> mpPage,@Param("pageReqVO") OrderPageReqVO pageReqVO);
}
