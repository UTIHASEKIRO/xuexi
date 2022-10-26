package com.zkzl.module.test.controller.admin.generate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 测试自动代码生成更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GenerateUpdateReqVO extends GenerateBaseVO {

    @ApiModelProperty(value = "参数主键", required = true)
    @NotNull(message = "参数主键不能为空")
    private Integer id;

}
