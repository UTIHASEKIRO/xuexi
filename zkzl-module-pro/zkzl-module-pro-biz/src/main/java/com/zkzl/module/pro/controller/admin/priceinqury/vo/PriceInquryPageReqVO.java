package com.zkzl.module.pro.controller.admin.priceinqury.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 询价分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PriceInquryPageReqVO extends PageParam {

    @ApiModelProperty(value = "询价表id")
    private String priceInquryId;

    @ApiModelProperty(value = "卖方公司名称")
    private String sellerCompanyName;

    @ApiModelProperty(value = "卖方联系地址")
    private String sellerCompanyAddress;

    @ApiModelProperty(value = "卖方联系人")
    private String sellerContact;

    @ApiModelProperty(value = "卖方联系电话")
    private String sellerTel;

    @ApiModelProperty(value = "客户id")
    private Long buyerCompanyId;

    @ApiModelProperty(value = "总价格")
    private BigDecimal price;

    @ApiModelProperty(value = "折扣")
    private BigDecimal discount;

    @ApiModelProperty(value = "合计")
    private BigDecimal total;

    @ApiModelProperty(value = "报价日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] priceDate;

    @ApiModelProperty(value = "报价有效日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] effectiveDate;

    @ApiModelProperty(value = "状态 1客户询价状态 2老板确认状态 3询价完成状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
