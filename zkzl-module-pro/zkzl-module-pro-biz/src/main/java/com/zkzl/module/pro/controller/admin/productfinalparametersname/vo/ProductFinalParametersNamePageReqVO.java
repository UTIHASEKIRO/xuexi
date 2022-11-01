package com.zkzl.module.pro.controller.admin.productfinalparametersname.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 产品固定参数名分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductFinalParametersNamePageReqVO extends PageParam {

    @ApiModelProperty(value = "参数名-中文")
    private String parameterCn;

    @ApiModelProperty(value = "参数名-英文")
    private String parameterEn;

}
