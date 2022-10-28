package com.zkzl.module.pro.controller.admin.productfinalparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 产品固定参数 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductFinalParametersRespVO extends ProductFinalParametersBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    private Long id;

}
