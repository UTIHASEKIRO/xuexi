package com.zkzl.module.pro.controller.admin.slidesshow.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 轮播图 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class SlidesShowExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("图片")
    private String picUrl;

    @ExcelProperty("创建时间")
    private Date createTime;

}
