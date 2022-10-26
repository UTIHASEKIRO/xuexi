package com.zkzl.module.minio.controller.admin.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - minio文件更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileUpdateReqVO extends FileBaseVO {

    @ApiModelProperty(value = "参数主键", required = true)
    @NotNull(message = "参数主键不能为空")
    private String md5;

}
