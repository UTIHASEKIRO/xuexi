package com.zkzl.module.pro.controller.app.product.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ApiModel("app - 产品详情 返回 VO")
@Data
@ToString(callSuper = true)
public class ProductDescVO{
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "产品业务id")
    private String productId;

    @ApiModelProperty(value = "产品类别id")
    private String typeId;

    @ApiModelProperty(value = "产品名称")
    private String productNameCn;

    @ApiModelProperty(value = "产品名称")
    private String productNameEn;

    @ApiModelProperty(value = "产品型号")
    private String productModel;

    @ApiModelProperty(value = "HS编码")
    private String hsNo;

    @ApiModelProperty(value = "包装方式-中文")
    private String packagingMethodCn;

    @ApiModelProperty(value = "包装方式-英文")
    private String packagingMethodEn;

    @ApiModelProperty(value = "颜色-中文")
    private String colourCn;

    @ApiModelProperty(value = "颜色-英文")
    private String colourEn;

    @ApiModelProperty(value = "用途分类-中文")
    private String useClassificationCn;
    /**
     * 用途分类-英文
     */
    private String useClassificationEn;
    /**
     * 交货方式-中文
     */
    private String deliveryMethodCn;
    /**
     * 交货方式-英文
     */
    private String deliveryMethodEn;
    /**
     * 克重
     */
    private String gramWeight;
    /**
     * 尺寸
     */
    private String size;
    /**
     * 箱规
     */
    private String boxGauge;
    /**
     * 体积
     */
    private String volume;
    /**
     * 毛重
     */
    private String grossWeight;
    /**
     * 净重
     */
    private String netWeight;

    @ApiModelProperty(value = "是否加入询价")
    private String isInqury;

    @ApiModelProperty(value = "产品图片")
    private List<UrlVO> urls;

    @ApiModelProperty(value = "产品图片")
    private List<CertificateVO> certs;

}




