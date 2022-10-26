package com.zkzl.module.test.controller.admin.generate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 测试自动代码生成创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class GenerateCreateReqVO extends GenerateBaseVO {

    private String con;
}
