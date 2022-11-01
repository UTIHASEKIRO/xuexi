package com.zkzl.module.pro.controller.admin.supplyinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 供货商信息 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class SupplyInfoExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("公司名称")
    private String name;

    @ExcelProperty("公司地址")
    private String address;

    @ExcelProperty("联系人")
    private String contact;

    @ExcelProperty("电话")
    private String phone;

    @ExcelProperty("email")
    private String email;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("主要产品")
    private String product;

    @ExcelProperty("产品类别id")
    private String typeId;

    @ExcelProperty("推荐理由-价格")
    private String reasonPrice;

    @ExcelProperty("推荐理由-质量")
    private String reasonQuality;

    @ExcelProperty("推荐理由-服务")
    private String reasonService;

    @ExcelProperty("公司评价")
    private String evaluation;

}
