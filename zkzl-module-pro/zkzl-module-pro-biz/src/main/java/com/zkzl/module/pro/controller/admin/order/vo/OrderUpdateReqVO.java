package com.zkzl.module.pro.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderUpdateReqVO extends OrderBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    @NotNull(message = "序号id不能为空")
    private Long id;

}
