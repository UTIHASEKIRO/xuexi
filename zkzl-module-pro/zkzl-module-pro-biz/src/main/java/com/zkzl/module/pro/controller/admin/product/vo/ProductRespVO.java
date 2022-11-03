package com.zkzl.module.pro.controller.admin.product.vo;

import com.zkzl.module.pro.dal.dataobject.productfinalparameters.ProductFinalParametersDO;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 产品 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductRespVO extends ProductBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "参数列表", required = true)
    private List<ProductParametersDO> parametersDOS;

    @ApiModelProperty(value = "图片列表", required = true)
    private List<ProductPicDO> picDOS;

}
