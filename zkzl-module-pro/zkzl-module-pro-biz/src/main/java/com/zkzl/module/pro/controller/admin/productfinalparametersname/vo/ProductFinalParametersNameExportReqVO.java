package com.zkzl.module.pro.controller.admin.productfinalparametersname.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 产品固定参数名 Excel 导出 Request VO", description = "参数和 ProductFinalParametersNamePageReqVO 是一致的")
@Data
public class ProductFinalParametersNameExportReqVO {

    @ApiModelProperty(value = "参数名-中文")
    private String parameterCn;

    @ApiModelProperty(value = "参数名-英文")
    private String parameterEn;

}
