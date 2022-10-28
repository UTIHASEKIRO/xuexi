package com.zkzl.module.pro.controller.admin.purchasehistory.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 供货商供应记录 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PurchaseHistoryRespVO extends PurchaseHistoryBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
