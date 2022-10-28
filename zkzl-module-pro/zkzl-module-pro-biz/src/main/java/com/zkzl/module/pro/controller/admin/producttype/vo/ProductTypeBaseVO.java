package com.zkzl.module.pro.controller.admin.producttype.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 商品类别 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProductTypeBaseVO {

    @ApiModelProperty(value = "父id")
    private String parentTypeId;

    @ApiModelProperty(value = "类别id")
    private String typeId;

    @ApiModelProperty(value = "类别名称")
    private String typeName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
