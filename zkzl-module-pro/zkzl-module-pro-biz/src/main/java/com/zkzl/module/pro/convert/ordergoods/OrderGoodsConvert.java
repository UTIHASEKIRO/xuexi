package com.zkzl.module.pro.convert.ordergoods;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.ordergoods.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordergoods.OrderGoodsDO;

/**
 * 订单商品关联表	 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderGoodsConvert {

    OrderGoodsConvert INSTANCE = Mappers.getMapper(OrderGoodsConvert.class);

    OrderGoodsDO convert(OrderGoodsCreateReqVO bean);

    OrderGoodsDO convert(OrderGoodsUpdateReqVO bean);

    OrderGoodsRespVO convert(OrderGoodsDO bean);

    List<OrderGoodsRespVO> convertList(List<OrderGoodsDO> list);

    PageResult<OrderGoodsRespVO> convertPage(PageResult<OrderGoodsDO> page);

    List<OrderGoodsExcelVO> convertList02(List<OrderGoodsDO> list);

}
