package com.zkzl.module.pro.controller.admin.productcertificate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 证书 Excel 导出 Request VO", description = "参数和 ProductCertificatePageReqVO 是一致的")
@Data
public class ProductCertificateExportReqVO {

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "证书地址")
    private String certificateUrl;

}
