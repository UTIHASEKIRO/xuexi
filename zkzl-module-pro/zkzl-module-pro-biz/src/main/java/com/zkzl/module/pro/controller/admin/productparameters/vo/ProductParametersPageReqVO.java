package com.zkzl.module.pro.controller.admin.productparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel("管理后台 - 产品参数分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductParametersPageReqVO extends PageParam {

    @ApiModelProperty(value = "参数id")
    private String productId;

    @ApiModelProperty(value = "父参数id")
    private Long parentParamId;

    @ApiModelProperty(value = "参数名")
    private String parameter;

    @ApiModelProperty(value = "值")
    private String value;

}
