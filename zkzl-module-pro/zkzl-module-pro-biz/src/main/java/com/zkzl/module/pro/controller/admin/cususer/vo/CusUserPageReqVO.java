package com.zkzl.module.pro.controller.admin.cususer.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 客户公司信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CusUserPageReqVO extends PageParam {

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "联系人")
    private String contectName;

    @ApiModelProperty(value = "营业执照")
    private String businessLicense;


    @ApiModelProperty(value = "状态 1禁止用户登录前端页面 0正常")
    private Integer status;
}
