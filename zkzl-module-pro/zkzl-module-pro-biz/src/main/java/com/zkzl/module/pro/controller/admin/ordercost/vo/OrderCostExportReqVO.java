package com.zkzl.module.pro.controller.admin.ordercost.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 订单成本 Excel 导出 Request VO", description = "参数和 OrderCostPageReqVO 是一致的")
@Data
public class OrderCostExportReqVO {

    @ApiModelProperty(value = "订单成本表业务id")
    private String orderCostId;

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "数量")
    private Integer mount;

    @ApiModelProperty(value = "供货商id")
    private String supplyInfoId;

    @ApiModelProperty(value = "成本价")
    private BigDecimal costPrice;

    @ApiModelProperty(value = "询价时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] priceDate;

    @ApiModelProperty(value = "开始时间(订单生成时间)")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] startTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] endTime;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
