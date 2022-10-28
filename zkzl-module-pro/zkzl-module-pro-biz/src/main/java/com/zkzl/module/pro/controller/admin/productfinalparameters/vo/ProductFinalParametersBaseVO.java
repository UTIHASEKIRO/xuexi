package com.zkzl.module.pro.controller.admin.productfinalparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 产品固定参数 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProductFinalParametersBaseVO {

    @ApiModelProperty(value = "固定参数id", required = true)
    @NotNull(message = "固定参数id不能为空")
    private String finalParametersId;

    @ApiModelProperty(value = "参数名-中文")
    private String parameterCn;

    @ApiModelProperty(value = "参数名-英文")
    private String parameterEn;

    @ApiModelProperty(value = "值-中文")
    private String valueCn;

    @ApiModelProperty(value = "值-英文")
    private String valueEn;

}
