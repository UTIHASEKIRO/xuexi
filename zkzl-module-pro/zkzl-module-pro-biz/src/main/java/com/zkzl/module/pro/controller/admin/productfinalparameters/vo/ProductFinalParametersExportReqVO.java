package com.zkzl.module.pro.controller.admin.productfinalparameters.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 产品固定参数 Excel 导出 Request VO", description = "参数和 ProductFinalParametersPageReqVO 是一致的")
@Data
public class ProductFinalParametersExportReqVO {

    @ApiModelProperty(value = "固定参数id")
    private String finalParametersId;

    @ApiModelProperty(value = "参数名-中文")
    private String parameterCn;

    @ApiModelProperty(value = "参数名-英文")
    private String parameterEn;

    @ApiModelProperty(value = "值-中文")
    private String valueCn;

    @ApiModelProperty(value = "值-英文")
    private String valueEn;

}
