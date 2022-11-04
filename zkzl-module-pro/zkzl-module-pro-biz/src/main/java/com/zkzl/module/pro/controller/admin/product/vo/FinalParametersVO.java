package com.zkzl.module.pro.controller.admin.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FinalParametersVO{

    @ApiModelProperty(value = "HS 编号", required = true)
    private String hsNO;

    @ApiModelProperty(value = "产品型号", required = true)
    private String productModel;

    @ApiModelProperty(value = "用途分类", required = true)
    private String useClassification;

    @ApiModelProperty(value = "颜色", required = true)
    private String colour;

    @ApiModelProperty(value = "包装方式", required = true)
    private String packagingMethod;

    @ApiModelProperty(value = "交货方式", required = true)
    private String deliveryMethod;

}
