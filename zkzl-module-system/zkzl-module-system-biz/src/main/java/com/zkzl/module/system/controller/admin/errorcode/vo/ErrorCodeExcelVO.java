package com.zkzl.module.system.controller.admin.errorcode.vo;

import com.zkzl.framework.excel.core.annotations.DictFormat;
import com.zkzl.framework.excel.core.convert.DictConvert;
//import com.zkzl.adminserver.modules.infra.enums.InfDictTypeConstants;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 错误码 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ErrorCodeExcelVO {

    @ExcelProperty("错误码编号")
    private Long id;

    @ExcelProperty(value = "错误码类型", converter = DictConvert.class)
    @DictFormat("inf_error_code_type") // TODO 芋艿：得思考下杂解决枚举值
    private Integer type;

    @ExcelProperty("应用名")
    private String applicationName;

    @ExcelProperty("错误码编码")
    private Integer code;

    @ExcelProperty("错误码错误提示")
    private String message;

    @ExcelProperty("备注")
    private String memo;

    @ExcelProperty("创建时间")
    private Date createTime;

}
