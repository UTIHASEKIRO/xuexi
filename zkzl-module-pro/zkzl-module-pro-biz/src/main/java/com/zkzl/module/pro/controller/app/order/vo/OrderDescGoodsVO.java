package com.zkzl.module.pro.controller.app.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@ApiModel("app - 订单详情-商品list 返回 VO")
@Data
@ToString(callSuper = true)
public class OrderDescGoodsVO {

    @ApiModelProperty(value = "pro_order_goods id")
    private Long id;

    @ApiModelProperty(value = "商品业务id")
    private String productId;

    @ApiModelProperty(value = "商品名称")
    private String productNameCn;

    @ApiModelProperty(value = "商品名称")
    private String productNameEn;

    @ApiModelProperty(value = "HS编码")
    private String hsSerial;
    @ApiModelProperty(value = "货描")
    private String productDesc;
    @ApiModelProperty(value = "产品详情-尺寸")
    private String productSize;
    @ApiModelProperty(value = "产品详情-颜色")
    private String productColor;
    @ApiModelProperty(value = "产品详情-克重")
    private String productG;
    @ApiModelProperty(value = "包装方式")
    private String packageWay;
    @ApiModelProperty(value = "箱规-长")
    private String boxLength;
    @ApiModelProperty(value = "箱规-高")
    private String boxHeight;
    @ApiModelProperty(value = "箱规-宽")
    private String boxWide;

    @ApiModelProperty(value = "数量")
    private Long mount;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "总价格")
    private BigDecimal price;

    @ApiModelProperty(value = "体积")
    private String volume;

    @ApiModelProperty(value = "毛重")
    private String grossWeight;

    @ApiModelProperty(value = "净重")
    private String netWeight;

}
