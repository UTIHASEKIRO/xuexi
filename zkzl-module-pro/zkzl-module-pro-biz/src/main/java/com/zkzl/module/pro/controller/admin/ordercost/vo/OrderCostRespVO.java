package com.zkzl.module.pro.controller.admin.ordercost.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 订单成本 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderCostRespVO extends OrderCostBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
