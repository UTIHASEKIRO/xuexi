package com.zkzl.module.pro.controller.admin.news.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import com.zkzl.framework.excel.core.annotations.DictFormat;
import com.zkzl.framework.excel.core.convert.DictConvert;


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

    @ExcelProperty("标题_中文")
    private String titleCn;

    @ExcelProperty("标题_英文")
    private String titleEn;

    @ExcelProperty("概要_中文")
    private String summaryCn;

    @ExcelProperty("概要_英文")
    private String summaryEn;

    @ExcelProperty("内容_中文")
    private String contentCn;

    @ExcelProperty("内容_英文")
    private String contentEn;

    @ExcelProperty(value = "1公司新闻2行业新闻3通知通告", converter = DictConvert.class)
    @DictFormat("news_type") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer newsType;

    @ExcelProperty(value = "是否上架 1上架0下架", converter = DictConvert.class)
    @DictFormat("is_shelf") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private String shelves;

    @ExcelProperty("创建时间")
    private Date createTime;

}
