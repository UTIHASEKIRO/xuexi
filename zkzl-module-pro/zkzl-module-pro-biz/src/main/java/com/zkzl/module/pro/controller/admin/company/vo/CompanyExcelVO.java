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

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("图片")
    private String picUrl;

    @ExcelProperty("公司名称")
    private String name;

    @ExcelProperty("公司介绍")
    private String introduce;

    @ExcelProperty("公司地址")
    private String address;

    @ExcelProperty("电话")
    private String mobile;

    @ExcelProperty("邮箱")
    private String email;

    @ExcelProperty("创建时间")
    private Date createTime;

}
