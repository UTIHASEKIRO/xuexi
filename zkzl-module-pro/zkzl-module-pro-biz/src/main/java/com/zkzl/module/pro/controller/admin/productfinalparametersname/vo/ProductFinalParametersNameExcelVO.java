package com.zkzl.module.pro.controller.admin.productfinalparametersname.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品固定参数名 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProductFinalParametersNameExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("参数名-中文")
    private String parameterCn;

    @ExcelProperty("参数名-英文")
    private String parameterEn;

}
