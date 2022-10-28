package com.zkzl.module.pro.controller.admin.productpic.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 产品图片分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductPicPageReqVO extends PageParam {

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "图片地址")
    private String url;

}
