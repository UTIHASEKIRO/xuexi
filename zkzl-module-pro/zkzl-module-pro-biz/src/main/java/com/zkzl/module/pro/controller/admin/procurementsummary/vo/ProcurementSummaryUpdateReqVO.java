package com.zkzl.module.pro.controller.admin.procurementsummary.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 采购汇总更新 Request VO")
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
public class ProcurementSummaryUpdateReqVO {

    @ApiModelProperty(value = "采购汇总表id", required = true)
    @NotNull(message = "采购汇总表id不能为空")
    private String procurementSummaryId;

    @ApiModelProperty(value = "采购单状态（0采购中1已完成）")
    private Integer status;

    @ApiModelProperty(value = "完成进度")
    private Integer finishPercent;

    @ApiModelProperty(value = "采购金额")
    private BigDecimal procurementAmount;

    @ApiModelProperty(value = "定金金额")
    private BigDecimal depositAmount;

    @ApiModelProperty(value = "尾款金额")
    private BigDecimal balanceAmount;

    @ApiModelProperty(value = "生产进度-生产日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date produceDate;

    @ApiModelProperty(value = "生产进度-印刷包装确认日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date confirmDate;

    @ApiModelProperty(value = "生产进度-结束日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @ApiModelProperty(value = "产品测试结果汇报")
    private String result;

    @ApiModelProperty(value = "物流运输-体积")
    private String volume;

    @ApiModelProperty(value = "物流运输-运费")
    private BigDecimal freight;

    @ApiModelProperty(value = "装柜时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date packTime;

    @ApiModelProperty(value = "物流运输-发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deleveryTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

}
