package com.zkzl.module.pro.controller.app.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
class UrlVO{

    @ApiModelProperty(value = "产品图片")
    private String url;
}