package com.zkzl.module.pro.controller.admin.procurementsummary.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 采购汇总 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProcurementSummaryExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("采购汇总表id")
    private String procurementSummaryId;

    @ExcelProperty("订单编号")
    private String orderId;

    @ExcelProperty("子订单编号")
    private String orderChildId;

    @ExcelProperty("供应商名称")
    private String supplyCompany;

    @ExcelProperty("完成进度")
    private Integer finishPercent;

    @ExcelProperty("采购员")
    private String buyer;

    @ExcelProperty("采购金额")
    private BigDecimal procurementAmount;

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

    @ExcelProperty("物流运输-体积")
    private String volume;

    @ExcelProperty("物流运输-运费")
    private BigDecimal freight;

    @ExcelProperty("物流运输-发货时间")
    private Date deleveryTime;

    @ExcelProperty("装柜时间")
    private Date packTime;

    @ExcelProperty("备注")
    private String remarks;

}
