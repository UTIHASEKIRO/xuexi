package com.zkzl.module.pro.controller.admin.procurementsummary.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 采购汇总 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProcurementSummaryBaseVO {

    @ApiModelProperty(value = "采购汇总表id", required = true)
    @NotNull(message = "采购汇总表id不能为空")
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
    private Date produceDate;

    @ApiModelProperty(value = "生产进度-印刷包装确认日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date confirmDate;

    @ApiModelProperty(value = "生产进度-结束日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endDate;

    @ApiModelProperty(value = "产品测试结果汇报")
    private String result;

    @ApiModelProperty(value = "物流运输-体积")
    private String volume;

    @ApiModelProperty(value = "物流运输-运费")
    private BigDecimal freight;

    @ApiModelProperty(value = "物流运输-发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date deleveryTime;

    @ApiModelProperty(value = "装柜时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date packTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "采购单状态（0采购中1已完成）")
    private Integer status;

}
