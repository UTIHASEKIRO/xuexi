package com.zkzl.module.pro.controller.admin.customermanage.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 客户管理跟进 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class CustomerManageExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("客户")
    private String customerName;

    @ExcelProperty("联系方式")
    private String mobile;

    @ExcelProperty("联系地址")
    private String address;

    @ExcelProperty("重要等级 1重要2一般3其它")
    private String importantLevel;

    @ExcelProperty("客户意向  1高2中3低")
    private String customerIntention;

    @ExcelProperty("客户需求，引号;分隔开")
    private String customerDemand;

    @ExcelProperty("客户跟进结果 1进行中2已签约3已放弃")
    private String customerResult;

    @ExcelProperty("备注")
    private String remark;

    @ExcelProperty("跟进次数")
    private Integer followNum;

    @ExcelProperty("跟进销售员")
    private String followUser;

    @ExcelProperty("创建时间")
    private Date createTime;

}
