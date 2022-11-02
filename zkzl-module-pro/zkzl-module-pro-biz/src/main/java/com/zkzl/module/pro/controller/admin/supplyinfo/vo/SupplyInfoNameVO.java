package com.zkzl.module.pro.controller.admin.supplyinfo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@ApiModel("管理后台 - 供货商名称")
@Data
@ToString(callSuper = true)
public class SupplyInfoNameVO{

    @ApiModelProperty(value = "供货商id")
    private Long id;

    @ApiModelProperty(value = "公司名称")
    private String name;

}
