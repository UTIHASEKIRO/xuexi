package com.zkzl.module.pro.controller.admin.productpic.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 产品图片 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductPicRespVO extends ProductPicBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    private Long id;

}
