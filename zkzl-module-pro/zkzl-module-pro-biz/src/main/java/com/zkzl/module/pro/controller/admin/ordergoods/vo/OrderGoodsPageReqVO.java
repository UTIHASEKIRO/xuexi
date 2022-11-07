package com.zkzl.module.pro.controller.admin.ordergoods.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 订单商品关联表	分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderGoodsPageReqVO extends PageParam {

    @ApiModelProperty(value = "子订单id")
    private String orderChildId;

    @ApiModelProperty(value = "订单表id")
    private String orderId;

    @ApiModelProperty(value = "产品id")
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

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
