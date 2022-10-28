package com.zkzl.module.pro.controller.admin.cususer.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 客户公司信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CusUserRespVO extends CusUserBaseVO {

    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

}
