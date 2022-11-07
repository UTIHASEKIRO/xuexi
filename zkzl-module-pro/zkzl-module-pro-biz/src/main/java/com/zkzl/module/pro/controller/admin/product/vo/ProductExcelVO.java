package com.zkzl.module.pro.controller.admin.product.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProductExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("产品id")
    private String productId;

    @ExcelProperty("产品类别id")
    private String typeId;

    @ExcelProperty("产品名称-中文")
    private String productNameCn;

    @ExcelProperty("产品名称-英文")
    private String productNameEn;

    @ExcelProperty("产品型号")
    private String productModel;

    @ExcelProperty("HS编码")
    private String hsNo;

    @ExcelProperty("包装方式-中文")
    private String packagingMethodCn;

    @ExcelProperty("包装方式-英文")
    private String packagingMethodEn;

    @ExcelProperty("颜色-中文")
    private String colourCn;

    @ExcelProperty("颜色-英文")
    private String colourEn;

    @ExcelProperty("用途分类-中文")
    private String useClassificationCn;

    @ExcelProperty("用途分类-英文")
    private String useClassificationEn;

    @ExcelProperty("交货方式-中文")
    private String deliveryMethodCn;

    @ExcelProperty("交货方式-英文")
    private String deliveryMethodEn;

    @ExcelProperty("克重")
    private String gramWeight;

    @ExcelProperty("尺寸")
    private String size;

    @ExcelProperty("箱规")
    private String boxGauge;

    @ExcelProperty("体积")
    private String volume;

    @ExcelProperty("毛重")
    private String grossWeight;

    @ExcelProperty("净重")
    private String netWeight;

    @ExcelProperty("1上架0下架")
    private String shipped;

    @ExcelProperty("创建时间")
    private Date createTime;

}
