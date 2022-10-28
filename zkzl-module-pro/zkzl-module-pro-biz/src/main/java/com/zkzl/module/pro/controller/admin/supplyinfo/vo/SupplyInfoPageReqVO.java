package com.zkzl.module.pro.controller.admin.supplyinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 供货商信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SupplyInfoPageReqVO extends PageParam {

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "公司地址")
    private String address;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "email")
    private String email;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

    @ApiModelProperty(value = "主要产品")
    private String product;

    @ApiModelProperty(value = "产品类别id")
    private String sortId;

    @ApiModelProperty(value = "推荐理由-价格")
    private String reasonPrice;

    @ApiModelProperty(value = "推荐理由-质量")
    private String reasonQuality;

    @ApiModelProperty(value = "推荐理由-服务")
    private String reasonService;

}
