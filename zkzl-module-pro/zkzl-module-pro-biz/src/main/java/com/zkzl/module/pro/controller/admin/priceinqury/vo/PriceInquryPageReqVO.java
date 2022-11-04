package com.zkzl.module.pro.controller.admin.priceinqury.vo;

import com.alibaba.excel.annotation.ExcelProperty;
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

    @ApiModelProperty(value = "客户理想价格")
    private String buyerIdealPrice;

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

    @ApiModelProperty("0初始化状态 需要业务员补充卖方信息* 1客户询价后状态  需要管理员即老板报价" +
            "* 2老板确认后状态  待签约" +
            "    * 3客户确认  生成订单" +
            "    * 4放弃状态  客户存在恶意询价或老板拒签" +
            "    * */")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

    @ApiModelProperty(value = "买方公司名称")
    private String buyerCompanyName;

    @ApiModelProperty(value = "登陆人id")
    private Long LoginUserId;


}
