package com.zkzl.module.pro.controller.admin.productpic.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 产品图片 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProductPicExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("产品id")
    private String productId;

    @ExcelProperty("图片地址")
    private String url;

}
