package com.zkzl.module.pro.controller.admin.ordersummary.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkzl.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 订单汇总分页 VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderSummaryPageVO extends PageParam {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "询价单id")
    private String priceInquryId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "联系人")
    private String contectName;

    @ApiModelProperty(value = "联系电话")
    private String mobile;

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

    @ApiModelProperty(value = "1待生产-2生产完成-3待装柜-4待开船-5待到港-6最后完成")
    private String status;

    @ApiModelProperty(value = "签约时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] signTime;

    @ApiModelProperty(value = "签约时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date createTime;


}
