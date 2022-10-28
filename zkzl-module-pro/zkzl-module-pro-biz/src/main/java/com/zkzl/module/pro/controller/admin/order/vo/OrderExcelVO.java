package com.zkzl.module.pro.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 订单 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class OrderExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("订单id")
    private String orderId;

    @ExcelProperty("卖方公司名称")
    private String sellerCompanyName;

    @ExcelProperty("卖方联系地址")
    private String sellerCompanyAddress;

    @ExcelProperty("卖方联系人")
    private String sellerContact;

    @ExcelProperty("卖方联系电话")
    private String sellerTel;

    @ExcelProperty("客户id")
    private Long userId;

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

    @ExcelProperty("说明")
    private String explain;

    @ExcelProperty("1待生产-2生产完成-3待装柜-4待开船-5待到港-6最后完成")
    private String status;

    @ExcelProperty("创建时间")
    private Date createTime;

}
