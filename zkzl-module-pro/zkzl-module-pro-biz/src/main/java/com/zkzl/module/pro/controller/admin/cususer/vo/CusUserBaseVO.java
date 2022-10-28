package com.zkzl.module.pro.controller.admin.cususer.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 客户公司信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CusUserBaseVO {

    @ApiModelProperty(value = "联系地址", required = true)
    @NotNull(message = "联系地址不能为空")
    private String address;

    @ApiModelProperty(value = "公司名称", required = true)
    @NotNull(message = "公司名称不能为空")
    private String companyName;

    @ApiModelProperty(value = "联系人", required = true)
    @NotNull(message = "联系人不能为空")
    private String contectName;

}
