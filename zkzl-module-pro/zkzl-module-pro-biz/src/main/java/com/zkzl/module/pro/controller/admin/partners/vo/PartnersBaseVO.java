package com.zkzl.module.pro.controller.admin.partners.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 合作伙伴 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PartnersBaseVO {

    @ApiModelProperty(value = "图片")
    private String picUrl;

    @ApiModelProperty(value = "合作伙伴名称", required = true)
    @NotNull(message = "合作伙伴名称不能为空")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String introduce;

}
