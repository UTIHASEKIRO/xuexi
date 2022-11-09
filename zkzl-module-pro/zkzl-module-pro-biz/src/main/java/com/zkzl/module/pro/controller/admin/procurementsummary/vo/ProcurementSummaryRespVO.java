package com.zkzl.module.pro.controller.admin.procurementsummary.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 采购汇总 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcurementSummaryRespVO extends ProcurementSummaryBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "产品名称", required = true)
    private String productName;

    @ApiModelProperty(value = "数量")
    private Integer mount;
}
