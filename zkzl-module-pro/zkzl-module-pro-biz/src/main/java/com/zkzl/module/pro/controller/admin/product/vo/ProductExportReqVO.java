package com.zkzl.module.pro.controller.admin.product.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 产品 Excel 导出 Request VO", description = "参数和 ProductPageReqVO 是一致的")
@Data
public class ProductExportReqVO {

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "产品类别id")
    private String typeId;

    @ApiModelProperty(value = "产品名称-中文")
    private String productNameCn;

    @ApiModelProperty(value = "产品名称-英文")
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

    @ApiModelProperty(value = "用途分类-英文")
    private String useClassificationEn;

    @ApiModelProperty(value = "交货方式-中文")
    private String deliveryMethodCn;

    @ApiModelProperty(value = "交货方式-英文")
    private String deliveryMethodEn;

    @ApiModelProperty(value = "克重")
    private String gramWeight;

    @ApiModelProperty(value = "尺寸")
    private String size;

    @ApiModelProperty(value = "箱规")
    private String boxGauge;

    @ApiModelProperty(value = "体积")
    private String volume;

    @ApiModelProperty(value = "毛重")
    private String grossWeight;

    @ApiModelProperty(value = "净重")
    private String netWeight;

    @ApiModelProperty(value = "1上架0下架")
    private String shipped;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
