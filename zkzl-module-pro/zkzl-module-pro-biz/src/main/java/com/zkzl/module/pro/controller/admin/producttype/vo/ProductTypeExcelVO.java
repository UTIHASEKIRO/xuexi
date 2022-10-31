package com.zkzl.module.pro.controller.admin.producttype.vo;

import lombok.*;
import java.util.*;

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
    private Long parentId;

    @ExcelProperty("类别业务id")
    private String typeId;

    @ExcelProperty("类别名称-中文")
    private String typeNameCn;

    @ExcelProperty("类别名称-英文")
    private String typeNameEn;

    @ExcelProperty("排序")
    private Integer sort;

    @ExcelProperty("创建时间")
    private Date createTime;

}
