package com.zkzl.module.pro.controller.admin.productcertificate.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 证书 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class ProductCertificateExcelVO {

    @ExcelProperty("序号id")
    private Long id;

    @ExcelProperty("产品id")
    private String productId;

    @ExcelProperty("证书地址")
    private String certificateUrl;

}
