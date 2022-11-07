package com.zkzl.module.pro.controller.admin.productcertificate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 证书分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductCertificatePageReqVO extends PageParam {

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "证书地址")
    private String certificateUrl;

}
