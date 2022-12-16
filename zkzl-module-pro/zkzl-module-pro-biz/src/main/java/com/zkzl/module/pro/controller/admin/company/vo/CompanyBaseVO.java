package com.zkzl.module.pro.controller.admin.company.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 公司信息维护 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CompanyBaseVO {

    @ApiModelProperty(value = "图片", required = true)
    @NotNull(message = "图片不能为空")
    private String picUrl;

    @ApiModelProperty(value = "电话", required = true)
    @NotNull(message = "电话不能为空")
    private String mobile;

    @ApiModelProperty(value = "邮箱", required = true)
    @NotNull(message = "邮箱不能为空")
    private String email;

    @ApiModelProperty(value = "公司名称_中文", required = true)
    @NotNull(message = "公司名称_中文不能为空")
    private String nameCn;

    @ApiModelProperty(value = "公司名称_英文", required = true)
    @NotNull(message = "公司名称_英文不能为空")
    private String nameEn;

    @ApiModelProperty(value = "公司地址_中文", required = true)
    @NotNull(message = "公司地址_中文不能为空")
    private String addressCn;

    @ApiModelProperty(value = "公司地址_英文", required = true)
    @NotNull(message = "公司地址_英文不能为空")
    private String addressEn;

    @ApiModelProperty(value = "公司介绍_中文", required = true)
    @NotNull(message = "公司介绍_中文不能为空")
    private String introduceCn;

    @ApiModelProperty(value = "公司介绍_英文", required = true)
    @NotNull(message = "公司介绍_英文不能为空")
    private String introduceEn;

    @ApiModelProperty(value = "业务介绍_中文", required = true)
    @NotNull(message = "业务介绍_中文不能为空")
    private String businessIntroduceCn;

    @ApiModelProperty(value = "业务介绍_英文", required = true)
    @NotNull(message = "业务介绍_英文不能为空")
    private String businessIntroduceEn;
}
