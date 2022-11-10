package com.zkzl.module.pro.controller.app.priceinqury.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkzl.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel("app - 询价历史 返回 VO")
@Data
@ToString(callSuper = true)
public class PriceInquryHistoryVO extends PageParam{
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "询价表id")
    private String priceInquryId;

    @ApiModelProperty(value = "客户理想价格")
    private String buyerIdealPrice;

    @ApiModelProperty(value = "询价时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "报价日期", required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date priceDate;

    @ApiModelProperty(value = "总价格")
    private BigDecimal price;

    @ApiModelProperty(value = " 0初始化状态 需要业务员补充卖方信息" +
            "    * 1客户询价后状态  需要管理员即老板报价" +
            "    * 2老板确认后状态  待签约" +
            "    * 3客户确认  生成订单" +
            "    * 4放弃状态  客户存在恶意询价或老板拒签" +
            "    * ")
    private String status;

}
