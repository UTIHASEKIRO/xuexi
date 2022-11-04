package com.zkzl.module.pro.controller.admin.product.vo;

import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 产品 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProductBaseVO extends FinalParametersVO  {

    @ApiModelProperty(value = "产品id", required = true)
    private String productId;

    @ApiModelProperty(value = "产品类别id", required = true)
    private String typeId;

    @ApiModelProperty(value = "中文产品名称", required = true)
    @NotNull(message = "中文产品名称不能为空")
    private String productNameCn;

    @ApiModelProperty(value = "英文产品名称", required = true)
    @NotNull(message = "英文产品名称不能为空")
    private String productNameEn;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态")
    private String shipped;

    @ApiModelProperty(value = "参数", required = true)
    private List<ParametersVO> parametersVOList;

    @ApiModelProperty(value = "图片", required = true)
    private List<ProductPicDO> picDOS;

}
