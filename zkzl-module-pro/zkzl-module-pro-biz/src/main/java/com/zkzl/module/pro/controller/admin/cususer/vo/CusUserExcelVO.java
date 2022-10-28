package com.zkzl.module.pro.controller.admin.cususer.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 客户公司信息 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class CusUserExcelVO {

    @ExcelProperty("用户ID")
    private Long userId;

    @ExcelProperty("联系地址")
    private String address;

    @ExcelProperty("公司名称")
    private String companyName;

    @ExcelProperty("联系人")
    private String contectName;

}
