package com.zkzl.module.pro.controller.app.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
class CertificateVO {

    @ApiModelProperty(value = "产品证书")
    private String url;
}