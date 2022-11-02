package com.zkzl.module.pro.controller.admin.customermanage.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
import static com.zkzl.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

@ApiModel("管理后台 - 客户管理跟进 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomerManageRespVO extends CustomerManageBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
    private Date createTime;

}
