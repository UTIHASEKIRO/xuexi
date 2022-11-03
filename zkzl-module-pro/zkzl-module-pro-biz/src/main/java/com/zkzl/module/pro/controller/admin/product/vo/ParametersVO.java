package com.zkzl.module.pro.controller.admin.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ParametersVO {

    @ApiModelProperty(value = "参数id", required = true)
    private String productId;

    @ApiModelProperty(value = "父参数id", required = true)
    private Long parentParamId;

    @ApiModelProperty(value = "中文参数名")
    private String parameterCn;

    @ApiModelProperty(value = "英文参数名")
    private String parameterEn;

    @ApiModelProperty(value = "中文值")
    private String valueCn;

    @ApiModelProperty(value = "英文值")
    private String valueEn;

    @ApiModelProperty(value = "子参数")
    private List<ParametersVO> parametersVOS;

}
