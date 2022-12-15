package com.zkzl.module.pro.controller.admin.news.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
import static com.zkzl.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

@ApiModel("管理后台 - 新闻 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NewsRespVO extends NewsBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = TIME_ZONE_DEFAULT)
    private Date createTime;

}
