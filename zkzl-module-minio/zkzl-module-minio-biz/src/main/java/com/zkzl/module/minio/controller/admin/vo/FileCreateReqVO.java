package com.zkzl.module.minio.controller.admin.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - minio文件创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileCreateReqVO extends FileBaseVO {

    @ApiModelProperty(value = "参数主键", required = true)
    private String md5;
}
