package com.zkzl.module.pro.dal.dataobject.product;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品 DO
 *
 * @author 芋道源码
 */
@TableName("pro_product")
@KeySequence("pro_product_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 产品类别id
     */
    private String typeId;
    /**
     * 产品名称-中文
     */
    private String productNameCn;
    /**
     * 产品名称-英文
     */
    private String productNameEn;
    /**
     * 产品型号
     */
    private String productModel;
    /**
     * HS编码
     */
    private String hsNo;
    /**
     * 包装方式-中文
     */
    private String packagingMethodCn;
    /**
     * 包装方式-英文
     */
    private String packagingMethodEn;
    /**
     * 颜色-中文
     */
    private String colourCn;
    /**
     * 颜色-英文
     */
    private String colourEn;
    /**
     * 用途分类-中文
     */
    private String useClassificationCn;
    /**
     * 用途分类-英文
     */
    private String useClassificationEn;
    /**
     * 交货方式-中文
     */
    private String deliveryMethodCn;
    /**
     * 交货方式-英文
     */
    private String deliveryMethodEn;
    /**
     * 克重
     */
    private String gramWeight;
    /**
     * 尺寸
     */
    private String size;
    /**
     * 箱规
     */
    private String boxGauge;
    /**
     * 体积
     */
    private String volume;
    /**
     * 毛重
     */
    private String grossWeight;
    /**
     * 净重
     */
    private String netWeight;
    /**
     * 1上架0下架
     */
    private String shipped;

}
