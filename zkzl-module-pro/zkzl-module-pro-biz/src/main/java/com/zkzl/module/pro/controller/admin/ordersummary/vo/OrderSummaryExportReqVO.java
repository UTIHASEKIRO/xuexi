package com.zkzl.module.pro.controller.admin.ordersummary.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 订单汇总 Excel 导出 Request VO", description = "参数和 OrderSummaryPageReqVO 是一致的")
@Data
public class OrderSummaryExportReqVO {

    @ApiModelProperty(value = "订单汇总表id")
    private String orderSummaryId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "客户")
    private String customer;

    @ApiModelProperty(value = "完成进度")
    private Integer finishPercent;

    @ApiModelProperty(value = "业务员")
    private String salesman;

    @ApiModelProperty(value = "合同金额")
    private BigDecimal contractAmount;

    @ApiModelProperty(value = "定金金额")
    private BigDecimal depositAmount;

    @ApiModelProperty(value = "尾款金额")
    private BigDecimal balanceAmount;

    @ApiModelProperty(value = "生产进度-生产日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] produceDate;

    @ApiModelProperty(value = "生产进度-印刷包装确认日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] confirmDate;

    @ApiModelProperty(value = "生产进度-结束日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] endDate;

    @ApiModelProperty(value = "产品测试结果汇报")
    private String result;

    @ApiModelProperty(value = "货代-体积")
    private String volume;

    @ApiModelProperty(value = "货代-装柜时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] packTime;

    @ApiModelProperty(value = "货代-发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] deleveryTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] endingTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
