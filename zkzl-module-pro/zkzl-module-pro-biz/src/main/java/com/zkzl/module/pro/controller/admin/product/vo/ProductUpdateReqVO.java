package com.zkzl.module.pro.controller.admin.product.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 产品更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductUpdateReqVO extends ProductBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

}
