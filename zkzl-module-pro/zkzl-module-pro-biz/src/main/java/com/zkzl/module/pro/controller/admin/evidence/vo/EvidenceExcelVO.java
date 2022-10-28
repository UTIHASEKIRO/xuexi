package com.zkzl.module.pro.controller.admin.evidence.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 订单凭证信息 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class EvidenceExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("凭证id")
    private String evidenceId;

    @ExcelProperty("询价单id")
    private String priceInquryId;

    @ExcelProperty("订单id")
    private String orderId;

    @ExcelProperty("定金截图")
    private String depositPic;

    @ExcelProperty("尾款截图")
    private String balancePic;

}
