package com.zkzl.module.pro.controller.admin.productparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品参数 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProductParametersExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("参数id")
    private String productId;

    @ExcelProperty("父参数id")
    private Long parentParamId;

    @ExcelProperty("参数名")
    private String parameter;

    @ExcelProperty("值")
    private String value;

}
