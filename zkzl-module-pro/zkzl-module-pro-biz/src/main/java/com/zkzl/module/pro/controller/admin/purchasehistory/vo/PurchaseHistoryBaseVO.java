package com.zkzl.module.pro.controller.admin.purchasehistory.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 供货商供应记录 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PurchaseHistoryBaseVO {

    @ApiModelProperty(value = "供货商")
    private String supplyCompany;

    @ApiModelProperty(value = "供应记录ID-即供货单号")
    private String purchaseHistoryId;

    @ApiModelProperty(value = "产品编号")
    private String productSerial;

    @ApiModelProperty(value = "HS编号")
    private String hsSerial;

    @ApiModelProperty(value = "产品-尺寸")
    private String productSize;

    @ApiModelProperty(value = "产品-颜色")
    private String productColor;

    @ApiModelProperty(value = "产品-克重")
    private String productG;

    @ApiModelProperty(value = "箱规-长")
    private BigDecimal boxLength;

    @ApiModelProperty(value = "箱规-宽")
    private BigDecimal boxWide;

    @ApiModelProperty(value = "箱规-高")
    private BigDecimal boxHeight;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "毛重")
    private BigDecimal grossWeight;

    @ApiModelProperty(value = "净重")
    private BigDecimal netWeight;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "数量")
    private BigDecimal mount;

    @ApiModelProperty(value = "总价")
    private BigDecimal price;

}
