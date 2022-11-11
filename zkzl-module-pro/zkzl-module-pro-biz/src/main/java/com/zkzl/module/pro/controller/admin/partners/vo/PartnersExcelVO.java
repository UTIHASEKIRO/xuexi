package com.zkzl.module.pro.controller.admin.partners.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 合作伙伴 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class PartnersExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("图片")
    private String picUrl;

    @ExcelProperty("合作伙伴名称")
    private String name;

    @ExcelProperty("介绍")
    private String introduce;

    @ExcelProperty("创建时间")
    private Date createTime;

}
