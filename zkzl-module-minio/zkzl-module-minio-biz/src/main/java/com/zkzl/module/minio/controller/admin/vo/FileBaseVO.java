package com.zkzl.module.minio.controller.admin.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* minio文件 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class FileBaseVO {

    @ApiModelProperty(value = "下载路径", required = true)
    @NotNull(message = "下载路径不能为空")
    private String url;

    @ApiModelProperty(value = "相对路径", required = true)
    @NotNull(message = "相对路径不能为空")
    private String path;

}
