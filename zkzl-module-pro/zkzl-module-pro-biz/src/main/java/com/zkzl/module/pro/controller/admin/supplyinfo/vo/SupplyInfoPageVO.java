package com.zkzl.module.pro.controller.admin.supplyinfo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@ApiModel("管理后台 - 供货商信息分页 返回VO")
@Data
@ToString(callSuper = true)
public class SupplyInfoPageVO{

    @ApiModelProperty(value = "供货商id")
    private Long id;

    @ApiModelProperty(value = "公司名称")
    private String name;

    @ApiModelProperty(value = "类别名称-中文")
    private String typeNameCn;

    @ApiModelProperty(value = "类别名称-英文")
    private String typeNameEn;

    @ApiModelProperty(value = "联系人")
    private String contact;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "推荐理由-价格")
    private String reasonPrice;

    @ApiModelProperty(value = "推荐理由-质量")
    private String reasonQuality;

    @ApiModelProperty(value = "推荐理由-服务")
    private String reasonService;

    @ApiModelProperty(value = "主要产品")
    private String product;

    @ApiModelProperty(value = "评价")
    private String evaluation;

}
