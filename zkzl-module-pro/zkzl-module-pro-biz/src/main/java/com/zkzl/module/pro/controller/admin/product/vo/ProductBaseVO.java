package com.zkzl.module.pro.controller.admin.product.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 产品 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProductBaseVO {

    @ApiModelProperty(value = "产品id", required = true)
    @NotNull(message = "产品id不能为空")
    private String productId;

    @ApiModelProperty(value = "产品类别id", required = true)
    @NotNull(message = "产品类别id不能为空")
    private String typeId;

    @ApiModelProperty(value = "产品名称", required = true)
    @NotNull(message = "产品名称不能为空")
    private String productName;

}
