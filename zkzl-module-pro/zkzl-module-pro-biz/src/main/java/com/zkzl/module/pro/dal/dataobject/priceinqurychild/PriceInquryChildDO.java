package com.zkzl.module.pro.dal.dataobject.priceinqurychild;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 询价表子 DO
 *
 * @author 芋道源码
 */
@TableName("pro_price_inqury_child")
@KeySequence("pro_price_inqury_child_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceInquryChildDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 询价表id
     */
    private String priceInquryId;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 供应商业务id
     */
    private String supplyInfoId;
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
    private String volume;
    /**
     * 总价格
     */
    private BigDecimal price;
    /**
     * 毛重
     */
    private String grossWeight;
    /**
     * 净重
     */
    private String netWeight;
    /**
     * 数量
     */
    private Integer mount;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 备注
     */
    private String remark;

}
