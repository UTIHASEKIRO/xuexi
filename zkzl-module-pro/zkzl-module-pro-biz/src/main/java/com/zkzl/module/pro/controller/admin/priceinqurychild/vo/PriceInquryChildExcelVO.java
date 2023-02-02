package com.zkzl.module.pro.controller.admin.priceinqurychild.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 询价表子 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class PriceInquryChildExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("询价表id")
    private String priceInquryId;

    @ExcelProperty("产品id")
    private String productId;

    @ExcelProperty("HS编码")
    private String hsSerial;

    @ExcelProperty("货描")
    private String productDesc;

    @ExcelProperty("产品详情-尺寸")
    private String productSize;

    @ExcelProperty("产品详情-颜色")
    private String productColor;

    @ExcelProperty("产品详情-克重")
    private String productG;

    @ExcelProperty("包装方式")
    private String packageWay;

    @ExcelProperty("箱规-长")
    private BigDecimal boxLength;

    @ExcelProperty("箱规-高")
    private BigDecimal boxHeight;

    @ExcelProperty("箱规-宽")
    private BigDecimal boxWide;

    @ExcelProperty("体积")
    private String volume;

    @ExcelProperty("总价格")
    private BigDecimal price;

    @ExcelProperty("毛重")
    private String grossWeight;

    @ExcelProperty("净重")
    private String netWeight;

    @ExcelProperty("数量")
    private Integer mount;

    @ExcelProperty("单价")
    private BigDecimal unitPrice;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("创建时间")
    private Date createTime;

}
