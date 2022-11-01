package com.zkzl.module.pro.controller.admin.productfinalparametersname.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 产品固定参数名 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductFinalParametersNameRespVO extends ProductFinalParametersNameBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    private Long id;

}
