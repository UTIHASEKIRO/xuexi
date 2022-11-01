package com.zkzl.module.pro.controller.admin.productfinalparametersname.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 产品固定参数名 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProductFinalParametersNameBaseVO {

    @ApiModelProperty(value = "参数名-中文")
    private String parameterCn;

    @ApiModelProperty(value = "参数名-英文")
    private String parameterEn;

}
