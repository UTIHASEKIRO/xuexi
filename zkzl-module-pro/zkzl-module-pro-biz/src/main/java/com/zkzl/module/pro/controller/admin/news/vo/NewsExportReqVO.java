package com.zkzl.module.pro.controller.admin.news.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 新闻 Excel 导出 Request VO", description = "参数和 NewsPageReqVO 是一致的")
@Data
public class NewsExportReqVO {

    @ApiModelProperty(value = "图片")
    private String picUrl;

    @ApiModelProperty(value = "标题_中文")
    private String titleCn;

    @ApiModelProperty(value = "标题_英文")
    private String titleEn;

    @ApiModelProperty(value = "概要_中文")
    private String summaryCn;

    @ApiModelProperty(value = "概要_英文")
    private String summaryEn;

    @ApiModelProperty(value = "内容_中文")
    private String contentCn;

    @ApiModelProperty(value = "内容_英文")
    private String contentEn;

    @ApiModelProperty(value = "1公司新闻2行业新闻3通知通告")
    private Integer newsType;

    @ApiModelProperty(value = "是否上架 1上架0下架")
    private String shelves;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
