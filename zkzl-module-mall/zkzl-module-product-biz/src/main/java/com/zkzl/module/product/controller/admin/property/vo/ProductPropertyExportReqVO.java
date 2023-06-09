package com.zkzl.module.product.controller.admin.property.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 规格名称 Excel 导出 Request VO", description = "参数和 PropertyPageReqVO 是一致的")
@Data
public class ProductPropertyExportReqVO {

    @ApiModelProperty(value = "规格名称")
    private String name;

    @ApiModelProperty(value = "状态： 0 开启 ，1 禁用")
    private Integer status;

    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @ApiModelProperty(value = "创建时间")
    private Date[] createTime;

}
