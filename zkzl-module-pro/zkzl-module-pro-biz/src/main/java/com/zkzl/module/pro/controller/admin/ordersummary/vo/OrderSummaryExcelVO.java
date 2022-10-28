package com.zkzl.module.pro.controller.admin.ordersummary.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 订单汇总 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class OrderSummaryExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("订单汇总表id")
    private String orderSummaryId;

    @ExcelProperty("订单id")
    private String orderId;

    @ExcelProperty("客户")
    private String customer;

    @ExcelProperty("完成进度")
    private Integer finishPercent;

    @ExcelProperty("业务员")
    private String salesman;

    @ExcelProperty("合同金额")
    private BigDecimal contractAmount;

    @ExcelProperty("定金金额")
    private BigDecimal depositAmount;

    @ExcelProperty("尾款金额")
    private BigDecimal balanceAmount;

    @ExcelProperty("生产进度-生产日期")
    private Date produceDate;

    @ExcelProperty("生产进度-印刷包装确认日期")
    private Date confirmDate;

    @ExcelProperty("生产进度-结束日期")
    private Date endDate;

    @ExcelProperty("产品测试结果汇报")
    private String result;

    @ExcelProperty("货代-体积")
    private String volume;

    @ExcelProperty("货代-装柜时间")
    private Date packTime;

    @ExcelProperty("货代-发货时间")
    private Date deleveryTime;

    @ExcelProperty("结束时间")
    private Date endingTime;

    @ExcelProperty("备注")
    private String remarks;

}
