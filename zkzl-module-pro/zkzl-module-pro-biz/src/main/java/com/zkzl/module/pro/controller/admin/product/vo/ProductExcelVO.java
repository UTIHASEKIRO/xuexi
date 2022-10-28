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

    @ExcelProperty("产品名称")
    private String productName;

    @ExcelProperty("创建时间")
    private Date createTime;

}
