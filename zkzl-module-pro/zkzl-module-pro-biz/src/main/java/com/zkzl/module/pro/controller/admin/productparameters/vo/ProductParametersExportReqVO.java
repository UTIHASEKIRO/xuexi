package com.zkzl.module.pro.controller.admin.productparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 产品参数 Excel 导出 Request VO", description = "参数和 ProductParametersPageReqVO 是一致的")
@Data
public class ProductParametersExportReqVO {

    @ApiModelProperty(value = "参数id")
    private String productId;

    @ApiModelProperty(value = "父参数id")
    private Long parentParamId;

    @ApiModelProperty(value = "参数名")
    private String parameter;

    @ApiModelProperty(value = "值")
    private String value;

}
