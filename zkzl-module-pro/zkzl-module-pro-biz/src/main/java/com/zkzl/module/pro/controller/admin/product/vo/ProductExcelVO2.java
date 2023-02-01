package com.zkzl.module.pro.controller.admin.product.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentLoopMerge;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 产品 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProductExcelVO2 {

    @ExcelProperty("产品名称")
    @ContentLoopMerge(columnExtend = 2)
    @ColumnWidth(value = 18)
    private String productNameCn;

    @ExcelProperty("产品名称")
    @ColumnWidth(value = 18)
    private String productNameEn;

    @ExcelProperty("包装方式")
    @ContentLoopMerge(columnExtend = 2)
    @ColumnWidth(value = 18)
    private String packagingMethodCn;

    @ExcelProperty("包装方式")
    @ColumnWidth(value = 18)
    private String packagingMethodEn;

    @ExcelProperty("图片")
    @ColumnWidth(value = 38)
    private File pic;

    @ExcelProperty("产品型号")
    @ColumnWidth(value = 18)
    private String productModel;

    @ExcelProperty("颜色")
    @ColumnWidth(value = 18)
    @ContentLoopMerge(columnExtend = 2)
    private String colourCn;

    @ExcelProperty("颜色")
    @ColumnWidth(value = 18)
    private String colourEn;

    @ExcelProperty("用途分类")
    @ColumnWidth(value = 18)
    @ContentLoopMerge(columnExtend = 2)
    private String useClassificationCn;

    @ExcelProperty("用途分类")
    @ColumnWidth(value = 18)
    private String useClassificationEn;

    @ExcelProperty("交货方式")
    @ColumnWidth(value = 18)
    @ContentLoopMerge(columnExtend = 2)
    private String deliveryMethodCn;

    @ExcelProperty("交货方式")
    @ColumnWidth(value = 18)
    private String deliveryMethodEn;

    @ExcelProperty("HS编码")
    @ColumnWidth(value = 18)
    private String hsNo;


    @ExcelProperty("克重")
    @ColumnWidth(value = 18)
    private String gramWeight;

    @ExcelProperty("尺寸")
    @ColumnWidth(value = 18)
    private String size;

    @ExcelProperty("箱规")
    @ColumnWidth(value = 18)
    private String boxGauge;

    @ExcelProperty("体积")
    @ColumnWidth(value = 18)
    private String volume;

    @ExcelProperty("毛重")
    @ColumnWidth(value = 18)
    private BigDecimal grossWeight;

    @ExcelProperty("净重")
    @ColumnWidth(value = 18)
    private BigDecimal netWeight;

    @ExcelIgnore
    private String url;

}
