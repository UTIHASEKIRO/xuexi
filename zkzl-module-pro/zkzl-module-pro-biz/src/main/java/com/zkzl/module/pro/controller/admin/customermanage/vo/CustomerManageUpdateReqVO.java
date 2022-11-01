package com.zkzl.module.pro.controller.admin.customermanage.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 客户管理跟进更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomerManageUpdateReqVO extends CustomerManageBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    @NotNull(message = "序号id不能为空")
    private Long id;

}
