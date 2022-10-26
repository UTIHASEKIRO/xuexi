package com.zkzl.module.test.controller.admin.generate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 测试自动代码生成 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class GenerateExcelVO {

    @ExcelProperty("参数主键")
    private Integer id;

    @ExcelProperty("创建时间")
    private Date createTime;

}
