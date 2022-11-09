package com.zkzl.module.pro.controller.app.priceinqury.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ApiModel("app - 询价分页 返回 VO")
@Data
@ToString(callSuper = true)
public class PriceInquryAppPageVO {
    
    @ApiModelProperty(value = "询价表id")
    private Long id;

    @ApiModelProperty(value = "询价单号")
    private String priceInquryId;
    

}
