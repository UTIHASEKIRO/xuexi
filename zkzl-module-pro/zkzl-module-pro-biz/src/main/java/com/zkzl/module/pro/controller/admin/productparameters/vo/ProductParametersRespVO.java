package com.zkzl.module.pro.controller.admin.productparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 产品参数 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductParametersRespVO extends ProductParametersBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    private Long id;

}
