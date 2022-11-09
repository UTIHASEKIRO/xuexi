package com.zkzl.module.pro.controller.admin.priceinqury.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 询价分页 返回 VO")
@Data
@ToString(callSuper = true)
public class PriceInquryPageVO{

    @ApiModelProperty(value = "询价表id")
    private Long id;

    @ApiModelProperty(value = "询价单号")
    private String priceInquryId;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "联系人名称")
    private String contactName;

    @ApiModelProperty(value = "联系电话")
    private String mobile;

    @ApiModelProperty(value = "操作人")
    private String operator;

    @ApiModelProperty("0初始化状态 需要业务员补充卖方信息* 1客户询价后状态  需要管理员即老板报价" +
            "* 2老板确认后状态  待签约" +
            "    * 3客户确认  生成订单" +
            "    * 4放弃状态  客户存在恶意询价或老板拒签" +
            "    * */")
    private String status;

    @ApiModelProperty(value = "询价时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createTime;
}
