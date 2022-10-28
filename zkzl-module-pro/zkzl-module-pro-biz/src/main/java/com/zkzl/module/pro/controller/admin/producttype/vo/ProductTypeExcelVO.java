package com.zkzl.module.pro.controller.admin.producttype.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 商品类别 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProductTypeExcelVO {

    @ExcelProperty("主键")
    private Long id;

    @ExcelProperty("父id")
    private String parentTypeId;

    @ExcelProperty("类别id")
    private String typeId;

    @ExcelProperty("类别名称")
    private String typeName;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("创建时间")
    private Date createTime;

}
