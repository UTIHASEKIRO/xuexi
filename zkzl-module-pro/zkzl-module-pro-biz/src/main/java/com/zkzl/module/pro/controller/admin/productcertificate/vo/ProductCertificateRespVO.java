package com.zkzl.module.pro.controller.admin.productcertificate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 证书 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductCertificateRespVO extends ProductCertificateBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    private Long id;

}
