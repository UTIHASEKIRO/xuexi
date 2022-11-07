package com.zkzl.module.pro.controller.admin.ordergoods.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 订单商品关联表	 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class OrderGoodsBaseVO {

    @ApiModelProperty(value = "子订单id")
    private String orderChildId;

    @ApiModelProperty(value = "订单表id", required = true)
    @NotNull(message = "订单表id不能为空")
    private String orderId;

    @ApiModelProperty(value = "产品id", required = true)
    @NotNull(message = "产品id不能为空")
    private String productId;

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
    private BigDecimal boxLength;

    @ApiModelProperty(value = "箱规-高")
    private BigDecimal boxHeight;

    @ApiModelProperty(value = "箱规-宽")
    private BigDecimal boxWide;

    @ApiModelProperty(value = "数量")
    private Integer mount;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "总价格")
    private BigDecimal price;

    @ApiModelProperty(value = "体积")
    private BigDecimal volume;

    @ApiModelProperty(value = "毛重")
    private BigDecimal grossWeight;

    @ApiModelProperty(value = "净重")
    private BigDecimal netWeight;

    @ApiModelProperty(value = "备注")
    private String remark;

}
