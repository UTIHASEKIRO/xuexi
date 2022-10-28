package com.zkzl.module.pro.controller.admin.procurementsummary.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 采购汇总 Excel 导出 Request VO", description = "参数和 ProcurementSummaryPageReqVO 是一致的")
@Data
public class ProcurementSummaryExportReqVO {

    @ApiModelProperty(value = "采购汇总表id")
    private String procurementSummaryId;

    @ApiModelProperty(value = "订单编号")
    private String orderId;

    @ApiModelProperty(value = "子订单编号")
    private String orderChildId;

    @ApiModelProperty(value = "供应商名称")
    private String supplyCompany;

    @ApiModelProperty(value = "完成进度")
    private Integer finishPercent;

    @ApiModelProperty(value = "采购员")
    private String buyer;

    @ApiModelProperty(value = "采购金额")
    private BigDecimal procurementAmount;

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

    @ApiModelProperty(value = "物流运输-体积")
    private String volume;

    @ApiModelProperty(value = "物流运输-运费")
    private BigDecimal freight;

    @ApiModelProperty(value = "物流运输-发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] deleveryTime;

    @ApiModelProperty(value = "装柜时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] packTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
