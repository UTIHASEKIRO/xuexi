package com.zkzl.module.pro.controller.admin.customermanage.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 客户管理跟进 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CustomerManageBaseVO {

    @ApiModelProperty(value = "客户", required = true)
    @NotNull(message = "客户不能为空")
    private String customerName;

    @ApiModelProperty(value = "联系方式", required = true)
    @NotNull(message = "联系方式不能为空")
    private String mobile;

    @ApiModelProperty(value = "联系地址", required = true)
    @NotNull(message = "联系地址不能为空")
    private String address;

    @ApiModelProperty(value = "重要等级 1重要2一般3其它", required = true)
    @NotNull(message = "重要等级 1重要2一般3其它不能为空")
    private String importantLevel;

    @ApiModelProperty(value = "客户意向  1高2中3低")
    private String customerIntention;

    @ApiModelProperty(value = "客户需求，引号;分隔开")
    private String customerDemand;

    @ApiModelProperty(value = "客户跟进结果 1进行中2已签约3已放弃")
    private String customerResult;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "跟进次数")
    private Integer followNum;

    @ApiModelProperty(value = "跟进销售员", required = true)
    @NotNull(message = "跟进销售员不能为空")
    private String followUser;

}
