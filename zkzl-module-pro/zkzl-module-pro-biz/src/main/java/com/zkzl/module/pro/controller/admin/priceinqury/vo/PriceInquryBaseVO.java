package com.zkzl.module.pro.controller.admin.priceinqury.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 询价 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PriceInquryBaseVO {

    @ApiModelProperty(value = "询价表id", required = true)
    @NotNull(message = "询价表id不能为空")
    private String priceInquryId;

    @ApiModelProperty(value = "卖方公司名称", required = true)
    @NotNull(message = "卖方公司名称不能为空")
    private String sellerCompanyName;

    @ApiModelProperty(value = "卖方联系地址", required = true)
    @NotNull(message = "卖方联系地址不能为空")
    private String sellerCompanyAddress;

    @ApiModelProperty(value = "卖方联系人", required = true)
    @NotNull(message = "卖方联系人不能为空")
    private String sellerContact;

    @ApiModelProperty(value = "卖方联系电话", required = true)
    @NotNull(message = "卖方联系电话不能为空")
    private String sellerTel;

    @ApiModelProperty(value = "客户id", required = true)
    @NotNull(message = "客户id不能为空")
    private Long buyerCompanyId;

    @ApiModelProperty(value = "总价格")
    private BigDecimal price;

    @ApiModelProperty(value = "折扣")
    private BigDecimal discount;

    @ApiModelProperty(value = "合计")
    private BigDecimal total;

    @ApiModelProperty(value = "报价日期", required = true)
    @NotNull(message = "报价日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date priceDate;

    @ApiModelProperty(value = "报价有效日期", required = true)
    @NotNull(message = "报价有效日期不能为空")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date effectiveDate;

    @ApiModelProperty(value = "状态 1客户询价状态 2老板确认状态 3询价完成状态")
    private String status;

}
