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

    @ExcelProperty("包装方式")
    @ContentLoopMerge(columnExtend = 2)
    @ColumnWidth(value = 18)
    private String packagingMethodCn;

    @ExcelProperty("图片")
    @ColumnWidth(value = 38)
    private File pic;

    @ExcelProperty("克重")
    @ColumnWidth(value = 18)
    private String gramWeight;

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
