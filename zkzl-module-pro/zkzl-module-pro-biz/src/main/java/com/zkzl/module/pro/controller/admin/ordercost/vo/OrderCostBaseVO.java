package com.zkzl.module.pro.controller.admin.ordercost.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 订单成本 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class OrderCostBaseVO {

    @ApiModelProperty(value = "订单成本表业务id", required = true)
    @NotNull(message = "订单成本表业务id不能为空")
    private String orderCostId;

    @ApiModelProperty(value = "产品id", required = true)
    @NotNull(message = "产品id不能为空")
    private String productId;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "数量")
    private Integer mount;

    @ApiModelProperty(value = "供货商id", required = true)
    @NotNull(message = "供货商id不能为空")
    private String supplyInfoId;

    /*供应商*/
    private String supply;
    /*产品*/
    private String product;

    @ApiModelProperty(value = "成本价")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "询价时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date priceDate;

    @ApiModelProperty(value = "开始时间(订单生成时间)")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endTime;

}
