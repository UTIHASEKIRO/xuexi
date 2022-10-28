package com.zkzl.module.pro.controller.admin.productfinalparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品固定参数 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProductFinalParametersExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("固定参数id")
    private String finalParametersId;

    @ExcelProperty("参数名-中文")
    private String parameterCn;

    @ExcelProperty("参数名-英文")
    private String parameterEn;

    @ExcelProperty("值-中文")
    private String valueCn;

    @ExcelProperty("值-英文")
    private String valueEn;

}
