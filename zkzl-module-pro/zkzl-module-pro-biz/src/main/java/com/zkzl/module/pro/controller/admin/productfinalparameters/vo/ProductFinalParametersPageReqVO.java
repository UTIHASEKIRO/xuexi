package com.zkzl.module.pro.controller.admin.productfinalparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 产品固定参数分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductFinalParametersPageReqVO extends PageParam {

    @ApiModelProperty(value = "固定参数id")
    private String finalParametersId;

    @ApiModelProperty(value = "参数名-中文")
    private String parameterCn;

    @ApiModelProperty(value = "参数名-英文")
    private String parameterEn;

    @ApiModelProperty(value = "值-中文")
    private String valueCn;

    @ApiModelProperty(value = "值-英文")
    private String valueEn;

}
