package com.zkzl.module.pro.controller.admin.product.vo;

import com.zkzl.module.pro.dal.dataobject.productcertificate.ProductCertificateDO;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 产品 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ProductBaseVO {

    @ApiModelProperty(value = "产品id", required = true)
    private String productId;

    @ApiModelProperty(value = "产品类别id", required = true)
    private String typeId;

    @ApiModelProperty(value = "产品名称-中文", required = true)
    private String productNameCn;

    @ApiModelProperty(value = "产品名称-英文", required = true)
    private String productNameEn;

    @ApiModelProperty(value = "产品型号")
    private String productModel;

    @ApiModelProperty(value = "HS编码")
    private String hsNo;

    @ApiModelProperty(value = "包装方式-中文", required = true)
    private String packagingMethodCn;

    @ApiModelProperty(value = "包装方式-英文", required = true)
    private String packagingMethodEn;

    @ApiModelProperty(value = "颜色-中文", required = true)
    private String colourCn;

    @ApiModelProperty(value = "颜色-英文", required = true)
    private String colourEn;

    @ApiModelProperty(value = "用途分类-中文", required = true)
    private String useClassificationCn;

    @ApiModelProperty(value = "用途分类-英文", required = true)
    private String useClassificationEn;

    @ApiModelProperty(value = "交货方式-中文", required = true)
    private String deliveryMethodCn;

    @ApiModelProperty(value = "交货方式-英文", required = true)
    private String deliveryMethodEn;

    @ApiModelProperty(value = "克重", required = true)
    private String gramWeight;

    @ApiModelProperty(value = "尺寸", required = true)
    private String size;

    @ApiModelProperty(value = "箱规", required = true)
    private String boxGauge;

    @ApiModelProperty(value = "体积", required = true)
    private BigDecimal volume;

    @ApiModelProperty(value = "毛重", required = true)
    private BigDecimal grossWeight;

    @ApiModelProperty(value = "净重", required = true)
    private BigDecimal netWeight;

    @ApiModelProperty(value = "1上架0下架", required = true)
    private String shipped;

    @ApiModelProperty(value = "参数列表", required = true)
    private List<ProductParametersDO> productParametersDOS;

    @ApiModelProperty(value = "图片列表", required = true)
    private List<ProductPicDO> picDOS;

    @ApiModelProperty(value = "证书", required = true)
    private List<ProductCertificateDO> productCertificateDOS;

    @ApiModelProperty(value = "用途分类中文list", required = true)
    private List<String> useClassificationCns;

    @ApiModelProperty(value = "用途分类英文list", required = true)
    private List<String> useClassificationEns;

    /*
     *  数量
     * */
    private Long mount;
}
