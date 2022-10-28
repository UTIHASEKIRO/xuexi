package com.zkzl.module.pro.controller.admin.evidence.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 订单凭证信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvidencePageReqVO extends PageParam {

    @ApiModelProperty(value = "凭证id")
    private String evidenceId;

    @ApiModelProperty(value = "询价单id")
    private String priceInquryId;

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "定金截图")
    private String depositPic;

    @ApiModelProperty(value = "尾款截图")
    private String balancePic;

}
