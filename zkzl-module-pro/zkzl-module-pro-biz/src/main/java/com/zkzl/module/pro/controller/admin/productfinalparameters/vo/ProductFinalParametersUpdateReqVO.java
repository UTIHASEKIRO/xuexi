package com.zkzl.module.pro.controller.admin.productfinalparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 产品固定参数更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductFinalParametersUpdateReqVO extends ProductFinalParametersBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    @NotNull(message = "序号id不能为空")
    private Long id;

}
