package com.zkzl.module.pro.controller.admin.priceinqury.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PriceInquryChildsVO {
    /**
     * 询价子表id
     */
    private String id;
    /**
     * 产品id
     */
    private String productId;
    /**
     * HS编码
     */
    private String hsSerial;
    /**
     * 货描
     */
    private String productDesc;
    /**
     * 产品详情-尺寸
     */
    private String productSize;
    /**
     * 产品详情-颜色
     */
    private String productColor;
    /**
     * 产品详情-克重
     */
    private String productG;
    /**
     * 包装方式
     */
    private String packageWay;
    /**
     * 箱规-长
     */
    private BigDecimal boxLength;
    /**
     * 箱规-高
     */
    private BigDecimal boxHeight;
    /**
     * 箱规-宽
     */
    private BigDecimal boxWide;
    /**
     * 体积
     */
    private BigDecimal volume;
    /**
     * 总价格
     */
    private BigDecimal price;
    /**
     * 毛重
     */
    private BigDecimal grossWeight;
    /**
     * 净重
     */
    private BigDecimal netWeight;
    /*
     * 供应商
     * */
    private String supplyName;
    /*
    *  数量
    * */
    private Long mount;
}
