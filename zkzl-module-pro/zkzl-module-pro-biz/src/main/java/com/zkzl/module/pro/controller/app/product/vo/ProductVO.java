package com.zkzl.module.pro.controller.app.product.vo;

import com.zkzl.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ApiModel("app - 产品分页 返回 VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductVO extends PageParam {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "产品业务id")
    private String productId;

    @ApiModelProperty(value = "产品类别id")
    private String typeId;

    @ApiModelProperty(value = "产品名称")
    private String productNameCn;

    @ApiModelProperty(value = "是否加入询价")
    private String isInqury;

    @ApiModelProperty(value = "产品名称")
    private String productNameEn;

    @ApiModelProperty(value = "产品图片")
    private List<UrlVO> urls;

}



