package com.zkzl.module.minio.controller.admin.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - minio文件 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FileRespVO extends FileBaseVO {

    @ApiModelProperty(value = "参数主键", required = true)
    private String md5;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
