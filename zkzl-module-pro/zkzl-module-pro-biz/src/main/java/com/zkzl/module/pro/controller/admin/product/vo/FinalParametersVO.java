package com.zkzl.module.pro.controller.admin.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FinalParametersVO{

    @ApiModelProperty(value = "HS 编号", required = true)
    @NotNull(message = "HS 编号")
    private String hsNO;

    @ApiModelProperty(value = "产品型号", required = true)
    @NotNull(message = "产品型号")
    private String productModel;

    @ApiModelProperty(value = "用途分类", required = true)
    @NotNull(message = "用途分类")
    private String useClassification;

    @ApiModelProperty(value = "颜色", required = true)
    @NotNull(message = "颜色")
    private String colour;

    @ApiModelProperty(value = "包装方式", required = true)
    @NotNull(message = "包装方式")
    private String packagingMethod;

    @ApiModelProperty(value = "交货方式", required = true)
    @NotNull(message = "交货方式")
    private String deliveryMethod;

}
