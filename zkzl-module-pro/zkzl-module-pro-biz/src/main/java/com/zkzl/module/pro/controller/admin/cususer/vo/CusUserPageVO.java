package com.zkzl.module.pro.controller.admin.cususer.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zkzl.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.checkerframework.checker.formatter.qual.Format;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
import static com.zkzl.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

@ApiModel("管理后台 - 客户公司信息分页 返回 VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CusUserPageVO extends PageParam {

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "联系人")
    private String contactName;

    @ApiModelProperty(value = "联系电话")
    private String mobile;

    @ApiModelProperty(value = "注册时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
    private String createTime;


    @ApiModelProperty(value = "状态 1禁止用户登录前端页面 0正常")
    private Integer status;
}
