package com.zkzl.module.pro.controller.admin.procurementsummary.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class ProcurementSummaryStatisticsVO {

    @ApiModelProperty(value = "总数")
    private Integer totalCount;

    @ApiModelProperty(value = "进行中")
    private Integer afootCount;

    @ApiModelProperty(value = "已完成")
    private Integer finishCount;

}
