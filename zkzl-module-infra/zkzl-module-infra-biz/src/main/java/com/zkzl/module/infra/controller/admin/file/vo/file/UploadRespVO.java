package com.zkzl.module.infra.controller.admin.file.vo.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "管理后台 - 上传文件 VO")
public class UploadRespVO {

    @ApiModelProperty(value = "文件名", required = true, example = "zkzl.jpg")
    private String fileName;

    @ApiModelProperty(value = "文件 URL", required = true, example = "https://www.iocoder.cn/zkzl.jpg")
    private String fileUrl;
}
