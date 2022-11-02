package com.zkzl.module.pro.controller.admin.purchasehistory.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 供货商供应记录 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class PurchaseHistoryExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("供应记录ID-即供货单号")
    private String purchaseHistoryId;

    @ApiModelProperty(value = "供货商")
    private String supplyCompany;

    @ExcelProperty("产品编号")
    private String productSerial;

    @ExcelProperty("HS编号")
    private String hsSerial;

    @ExcelProperty("产品-尺寸")
    private String productSize;

    @ExcelProperty("产品-颜色")
    private String productColor;

    @ExcelProperty("产品-克重")
    private String productG;

    @ExcelProperty("箱规-长")
    private BigDecimal boxLength;

    @ExcelProperty("箱规-宽")
    private BigDecimal boxWide;

    @ExcelProperty("箱规-高")
    private BigDecimal boxHeight;

    @ExcelProperty("单价")
    private BigDecimal unitPrice;

    @ExcelProperty("毛重")
    private BigDecimal grossWeight;

    @ExcelProperty("净重")
    private BigDecimal netWeight;

    @ExcelProperty("创建时间")
    private Date createTime;

}
