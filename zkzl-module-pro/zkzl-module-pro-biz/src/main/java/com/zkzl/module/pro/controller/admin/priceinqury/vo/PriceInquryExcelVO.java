package com.zkzl.module.pro.controller.admin.priceinqury.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 询价 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class PriceInquryExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("询价表id")
    private String priceInquryId;

    @ExcelProperty("卖方公司名称")
    private String sellerCompanyName;

    @ExcelProperty("卖方联系地址")
    private String sellerCompanyAddress;

    @ExcelProperty("卖方联系人")
    private String sellerContact;

    @ExcelProperty("卖方联系电话")
    private String sellerTel;

    @ExcelProperty("客户id")
    private Long buyerCompanyId;

    @ExcelProperty("总价格")
    private BigDecimal price;

    @ExcelProperty("折扣")
    private BigDecimal discount;

    @ExcelProperty("合计")
    private BigDecimal total;

    @ExcelProperty("报价日期")
    private Date priceDate;

    @ExcelProperty("报价有效日期")
    private Date effectiveDate;

    @ExcelProperty("状态 1客户询价状态 2老板确认状态 3询价完成状态")
    private String status;

    @ExcelProperty("创建时间")
    private Date createTime;

}
