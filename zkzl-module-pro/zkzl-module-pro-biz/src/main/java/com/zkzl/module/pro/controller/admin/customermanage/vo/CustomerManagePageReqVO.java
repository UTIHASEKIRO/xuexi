package com.zkzl.module.pro.controller.admin.customermanage.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 客户管理跟进分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CustomerManagePageReqVO extends PageParam {

    @ApiModelProperty(value = "客户id")
    private Long userId;

    @ApiModelProperty(value = "重要等级 1重要2一般3其它")
    private String importantLevel;

    @ApiModelProperty(value = "客户意向  1高2中3低")
    private String customerIntention;

    @ApiModelProperty(value = "客户需求，引号;分隔开")
    private String customerDemand;

    @ApiModelProperty(value = "客户跟进结果 1进行中2已签约3已放弃")
    private String customerResult;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "跟进次数")
    private Integer followNum;

    @ApiModelProperty(value = "跟进销售员")
    private Long followUserId;

    @ApiModelProperty(value = "跟进销售员姓名")
    private String followUserName;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
