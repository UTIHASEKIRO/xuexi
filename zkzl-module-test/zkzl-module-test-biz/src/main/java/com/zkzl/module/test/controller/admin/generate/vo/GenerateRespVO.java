package com.zkzl.module.test.controller.admin.generate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 测试自动代码生成 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GenerateRespVO extends GenerateBaseVO {

    @ApiModelProperty(value = "参数主键", required = true)
    private Integer id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
