package com.zkzl.module.pro.controller.admin.partners.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 合作伙伴 Excel 导出 Request VO", description = "参数和 PartnersPageReqVO 是一致的")
@Data
public class PartnersExportReqVO {

    @ApiModelProperty(value = "图片")
    private String picUrl;

    @ApiModelProperty(value = "合作伙伴名称")
    private String name;

    @ApiModelProperty(value = "介绍")
    private String introduce;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
