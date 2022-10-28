package com.zkzl.module.pro.controller.admin.ordersummary.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 订单汇总 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderSummaryRespVO extends OrderSummaryBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

}
