package com.zkzl.module.pro.controller.admin.productpic.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 产品图片 Excel 导出 Request VO", description = "参数和 ProductPicPageReqVO 是一致的")
@Data
public class ProductPicExportReqVO {

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "图片地址")
    private String url;

}
