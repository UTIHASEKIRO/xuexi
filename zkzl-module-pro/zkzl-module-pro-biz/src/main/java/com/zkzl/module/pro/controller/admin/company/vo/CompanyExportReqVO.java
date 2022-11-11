package com.zkzl.module.pro.controller.admin.company.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 公司信息维护 Excel 导出 Request VO", description = "参数和 CompanyPageReqVO 是一致的")
@Data
public class CompanyExportReqVO {

    @ApiModelProperty(value = "图片")
    private String picUrl;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "公司介绍")
    private String introduce;

    @ApiModelProperty(value = "公司地址")
    private String address;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
