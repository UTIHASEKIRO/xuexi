package com.zkzl.module.pro.controller.admin.slidesshow.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 轮播图 Excel 导出 Request VO", description = "参数和 SlidesShowPageReqVO 是一致的")
@Data
public class SlidesShowExportReqVO {

    @ApiModelProperty(value = "图片")
    private String picUrl;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "1上架 0下架")
    private String isShelf;

}
