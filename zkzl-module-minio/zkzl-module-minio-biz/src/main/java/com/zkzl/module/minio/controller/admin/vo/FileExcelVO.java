package com.zkzl.module.minio.controller.admin.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * minio文件 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class FileExcelVO {

    @ExcelProperty("参数主键")
    private String md5;

    @ExcelProperty("下载路径")
    private String url;

    @ExcelProperty("相对路径")
    private String path;

    @ExcelProperty("创建时间")
    private Date createTime;

}
