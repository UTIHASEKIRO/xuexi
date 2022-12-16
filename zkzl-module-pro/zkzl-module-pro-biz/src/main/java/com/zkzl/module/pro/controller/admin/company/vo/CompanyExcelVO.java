package com.zkzl.module.pro.controller.admin.company.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 公司信息维护 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class CompanyExcelVO {

    @ExcelProperty("电话")
    private String mobile;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("公司名称_中文")
    private String nameCn;

    @ExcelProperty("公司名称_英文")
    private String nameEn;

    @ExcelProperty("公司地址_中文")
    private String addressCn;

    @ExcelProperty("公司地址_英文")
    private String addressEn;

}
