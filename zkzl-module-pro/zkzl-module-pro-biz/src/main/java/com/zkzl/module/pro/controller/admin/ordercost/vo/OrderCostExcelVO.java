package com.zkzl.module.pro.controller.admin.ordercost.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 订单成本 Excel VO
 *
 * @author admin2
 */
@Data
public class OrderCostExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("订单成本表业务id")
    private String orderCostId;

    @ExcelProperty("产品id")
    private String productId;

    @ExcelProperty("单价")
    private BigDecimal unitPrice;

    @ExcelProperty("数量")
    private Integer mount;

    @ExcelProperty("供货商id")
    private String supplyInfoId;

    @ExcelProperty("成本价")
    private BigDecimal costPrice;

    @ExcelProperty("询价时间")
    private Date priceDate;

    @ExcelProperty("开始时间(订单生成时间)")
    private Date startTime;

    @ExcelProperty("结束时间")
    private Date endTime;

    @ExcelProperty("创建时间")
    private Date createTime;

}
