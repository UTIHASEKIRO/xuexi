package com.zkzl.module.pro.controller.admin.purchasehistory.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 供货商供应记录 Excel 导出 Request VO", description = "参数和 PurchaseHistoryPageReqVO 是一致的")
@Data
public class PurchaseHistoryExportReqVO {

    @ApiModelProperty(value = "供应记录ID-即供货单号")
    private String purchaseHistoryId;

    @ApiModelProperty(value = "供货商")
    private String supplyCompany;

    @ApiModelProperty(value = "产品编号")
    private String productSerial;

    @ApiModelProperty(value = "HS编号")
    private String hsSerial;

    @ApiModelProperty(value = "产品-尺寸")
    private String productSize;

    @ApiModelProperty(value = "产品-颜色")
    private String productColor;

    @ApiModelProperty(value = "产品-克重")
    private String productG;

    @ApiModelProperty(value = "箱规-长")
    private BigDecimal boxLength;

    @ApiModelProperty(value = "箱规-宽")
    private BigDecimal boxWide;

    @ApiModelProperty(value = "箱规-高")
    private BigDecimal boxHeight;

    @ApiModelProperty(value = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(value = "毛重")
    private BigDecimal grossWeight;

    @ApiModelProperty(value = "净重")
    private BigDecimal netWeight;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
