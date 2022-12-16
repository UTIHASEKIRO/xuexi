package com.zkzl.module.pro.controller.admin.news.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 新闻 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class NewsExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("图片")
    private String picUrl;

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("内容")
    private String content;

    @ExcelProperty("创建时间")
    private Date createTime;

    @ExcelProperty("概要")
    private String summary;

    @ExcelProperty("1公司新闻2行业新闻3通知通告")
    private Integer newsType;

    @ExcelProperty("是否上架 1上架0下架")
    private String shelves;

}
