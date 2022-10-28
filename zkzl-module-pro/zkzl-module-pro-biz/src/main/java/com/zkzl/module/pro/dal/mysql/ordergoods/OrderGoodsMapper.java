package com.zkzl.module.pro.dal.mysql.ordergoods;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.ordergoods.OrderGoodsDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.ordergoods.vo.*;

/**
 * 订单商品关联表	 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderGoodsMapper extends BaseMapperX<OrderGoodsDO> {

    default PageResult<OrderGoodsDO> selectPage(OrderGoodsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderGoodsDO>()
                .eqIfPresent(OrderGoodsDO::getOrderChildId, reqVO.getOrderChildId())
                .eqIfPresent(OrderGoodsDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(OrderGoodsDO::getProductId, reqVO.getProductId())
                .eqIfPresent(OrderGoodsDO::getHsSerial, reqVO.getHsSerial())
                .eqIfPresent(OrderGoodsDO::getDesc, reqVO.getDesc())
                .eqIfPresent(OrderGoodsDO::getProductSize, reqVO.getProductSize())
                .eqIfPresent(OrderGoodsDO::getProductColor, reqVO.getProductColor())
                .eqIfPresent(OrderGoodsDO::getProductG, reqVO.getProductG())
                .eqIfPresent(OrderGoodsDO::getPackageWay, reqVO.getPackageWay())
                .eqIfPresent(OrderGoodsDO::getBoxLength, reqVO.getBoxLength())
                .eqIfPresent(OrderGoodsDO::getBoxHeight, reqVO.getBoxHeight())
                .eqIfPresent(OrderGoodsDO::getBoxWide, reqVO.getBoxWide())
                .eqIfPresent(OrderGoodsDO::getMount, reqVO.getMount())
                .eqIfPresent(OrderGoodsDO::getUnitPrice, reqVO.getUnitPrice())
                .eqIfPresent(OrderGoodsDO::getPrice, reqVO.getPrice())
                .eqIfPresent(OrderGoodsDO::getVolume, reqVO.getVolume())
                .eqIfPresent(OrderGoodsDO::getGrossWeight, reqVO.getGrossWeight())
                .eqIfPresent(OrderGoodsDO::getNetWeight, reqVO.getNetWeight())
                .eqIfPresent(OrderGoodsDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(OrderGoodsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderGoodsDO::getId));
    }

    default List<OrderGoodsDO> selectList(OrderGoodsExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<OrderGoodsDO>()
                .eqIfPresent(OrderGoodsDO::getOrderChildId, reqVO.getOrderChildId())
                .eqIfPresent(OrderGoodsDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(OrderGoodsDO::getProductId, reqVO.getProductId())
                .eqIfPresent(OrderGoodsDO::getHsSerial, reqVO.getHsSerial())
                .eqIfPresent(OrderGoodsDO::getDesc, reqVO.getDesc())
                .eqIfPresent(OrderGoodsDO::getProductSize, reqVO.getProductSize())
                .eqIfPresent(OrderGoodsDO::getProductColor, reqVO.getProductColor())
                .eqIfPresent(OrderGoodsDO::getProductG, reqVO.getProductG())
                .eqIfPresent(OrderGoodsDO::getPackageWay, reqVO.getPackageWay())
                .eqIfPresent(OrderGoodsDO::getBoxLength, reqVO.getBoxLength())
                .eqIfPresent(OrderGoodsDO::getBoxHeight, reqVO.getBoxHeight())
                .eqIfPresent(OrderGoodsDO::getBoxWide, reqVO.getBoxWide())
                .eqIfPresent(OrderGoodsDO::getMount, reqVO.getMount())
                .eqIfPresent(OrderGoodsDO::getUnitPrice, reqVO.getUnitPrice())
                .eqIfPresent(OrderGoodsDO::getPrice, reqVO.getPrice())
                .eqIfPresent(OrderGoodsDO::getVolume, reqVO.getVolume())
                .eqIfPresent(OrderGoodsDO::getGrossWeight, reqVO.getGrossWeight())
                .eqIfPresent(OrderGoodsDO::getNetWeight, reqVO.getNetWeight())
                .eqIfPresent(OrderGoodsDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(OrderGoodsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderGoodsDO::getId));
    }

}
