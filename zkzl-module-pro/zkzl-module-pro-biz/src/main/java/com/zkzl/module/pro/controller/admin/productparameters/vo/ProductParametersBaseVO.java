package com.zkzl.module.pro.controller.admin.productparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 产品参数 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProductParametersBaseVO {

    @ApiModelProperty(value = "参数id", required = true)
    @NotNull(message = "参数id不能为空")
    private String productId;

    @ApiModelProperty(value = "父参数id", required = true)
    @NotNull(message = "父参数id不能为空")
    private Long parentParamId;

    @ApiModelProperty(value = "参数名")
    private String parameter;

    @ApiModelProperty(value = "值")
    private String value;

}
