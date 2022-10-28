package com.zkzl.module.pro.controller.admin.evidence.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 订单凭证信息 Excel 导出 Request VO", description = "参数和 EvidencePageReqVO 是一致的")
@Data
public class EvidenceExportReqVO {

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
