package com.zkzl.module.pro.controller.admin.producttype.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 商品类别 Excel 导出 Request VO", description = "参数和 ProductTypePageReqVO 是一致的")
@Data
public class ProductTypeExportReqVO {

    @ApiModelProperty(value = "父id")
    private String parentTypeId;

    @ApiModelProperty(value = "类别id")
    private String typeId;

    @ApiModelProperty(value = "类别名称")
    private String typeName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
