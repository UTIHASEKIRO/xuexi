package com.zkzl.module.pro.controller.admin.supplyinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 供货商信息 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class SupplyInfoBaseVO {

    @ApiModelProperty(value = "供货商业务id")
    private String supplyInfoId;

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

    @ApiModelProperty(value = "主要产品")
    private String productId;

    @ApiModelProperty(value = "产品类别id")
    private String typeId;

    @ApiModelProperty(value = "推荐理由-价格")
    private String reasonPrice;

    @ApiModelProperty(value = "推荐理由-质量")
    private String reasonQuality;

    @ApiModelProperty(value = "推荐理由-服务")
    private String reasonService;

    @ApiModelProperty(value = "公司评价")
    private String evaluation;

}
