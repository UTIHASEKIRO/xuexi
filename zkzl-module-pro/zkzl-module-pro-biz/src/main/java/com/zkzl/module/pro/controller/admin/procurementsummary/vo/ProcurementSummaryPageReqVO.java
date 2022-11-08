package com.zkzl.module.pro.controller.admin.procurementsummary.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 采购汇总分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProcurementSummaryPageReqVO extends PageParam {

    @ApiModelProperty(value = "采购单状态")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date[] createTime;

}
