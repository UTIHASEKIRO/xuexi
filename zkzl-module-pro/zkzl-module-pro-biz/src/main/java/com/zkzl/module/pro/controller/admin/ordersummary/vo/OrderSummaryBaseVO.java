package com.zkzl.module.pro.controller.admin.ordersummary.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 订单汇总 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class OrderSummaryBaseVO {

    @ApiModelProperty(value = "询价单id")
    private String priceInquryId;

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
    @Digits(integer=6,fraction = 2,message = "整数位数最大6位，小数最大两位！")
    private BigDecimal contractAmount;

    @ApiModelProperty(value = "定金金额")
    @Digits(integer=6,fraction = 2,message = "整数位数最大6位，小数最大两位！")
    private BigDecimal depositAmount;

    @ApiModelProperty(value = "尾款金额")
    @Digits(integer=6,fraction = 2,message = "整数位数最大6位，小数最大两位！")
    private BigDecimal balanceAmount;

    @ApiModelProperty(value = "生产进度-生产日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date produceDate;

    @ApiModelProperty(value = "生产进度-印刷包装确认日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date confirmDate;

    @ApiModelProperty(value = "生产进度-结束日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endDate;

    @ApiModelProperty(value = "产品测试结果汇报")
    private String result;

    @ApiModelProperty(value = "货代-体积")
    private String volume;

    @ApiModelProperty(value = "货代-装柜时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date packTime;

    @ApiModelProperty(value = "货代-发货时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date deleveryTime;

    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date endingTime;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "1待生产-2生产完成-3待装柜-4待开船-5待到港-6最后完成")
    private String status;

    @ApiModelProperty(value = "创建时间-即签约时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date createTime;

}
