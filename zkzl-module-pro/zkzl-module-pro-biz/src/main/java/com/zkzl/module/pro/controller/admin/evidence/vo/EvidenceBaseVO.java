package com.zkzl.module.pro.controller.admin.evidence.vo;

import lombok.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 订单凭证信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class EvidenceBaseVO {

    @ApiModelProperty(value = "凭证id")
    private String evidenceId;

    @ApiModelProperty(value = "询价单id", required = true)
    @NotNull(message = "询价单id不能为空")
    private String priceInquryId;

    @ApiModelProperty(value = "订单id", required = true)
    @NotNull(message = "订单id不能为空")
    private String orderId;

    @ApiModelProperty(value = "定金截图")
    private String depositPic;

    @ApiModelProperty(value = "尾款截图")
    private String balancePic;

}
