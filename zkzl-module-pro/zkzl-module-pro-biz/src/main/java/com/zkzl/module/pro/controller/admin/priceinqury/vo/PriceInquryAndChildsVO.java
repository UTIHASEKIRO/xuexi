package com.zkzl.module.pro.controller.admin.priceinqury.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 询价单即子单 返回 VO")
@Data
@ToString(callSuper = true)
public class PriceInquryAndChildsVO {

    @ApiModelProperty(value = "询价表id")
    private Long id;

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

    @ApiModelProperty(value = "客户公司名称")
    private String buyerCompanyName;

    @ApiModelProperty(value = "客户地址")
    private String buyerAddress;

    @ApiModelProperty(value = "客户联系人")
    private String buyerContactName;

    @ApiModelProperty(value = "客户理想价格")
    private String buyerIdealPrice;

    @ApiModelProperty(value = "客户电话")
    private String buyerMobile;

    @ApiModelProperty(value = "报价日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date priceDate;

    @ApiModelProperty(value = "报价有效日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date effectiveDate;

    @ApiModelProperty(value = " 0初始化状态 需要业务员补充卖方信息" +
            "    * 1客户询价后状态  需要管理员即老板报价" +
            "    * 2老板确认后状态  待签约" +
            "    * 3客户确认  生成订单" +
            "    * 4放弃状态  客户存在恶意询价或老板拒签" +
            "    * ")
    private String status;

    @ApiModelProperty(value = "总价格")
    private BigDecimal price;

    @ApiModelProperty(value = "折扣")
    private BigDecimal discount;

    @ApiModelProperty(value = "合计")
    private BigDecimal total;

    @ApiModelProperty(value = "子表")
    private List<PriceInquryChildsVO> childs;
}



