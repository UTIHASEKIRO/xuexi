package com.zkzl.module.product.controller.admin.brand.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 品牌 Excel 导出 Request VO", description = "参数和 BrandPageReqVO 是一致的")
@Data
public class BrandExportReqVO {

    @ApiModelProperty(value = "分类编号", example = "1")
    private Long categoryId;

    @ApiModelProperty(value = "品牌名称", example = "芋道")
    private String name;

    @ApiModelProperty(value = "状态", example = "0")
    private Integer status;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "创建时间")
    private Date[] createTime;

}
