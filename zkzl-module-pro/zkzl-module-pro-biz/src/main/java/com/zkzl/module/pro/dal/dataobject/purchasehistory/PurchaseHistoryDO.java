package com.zkzl.module.pro.dal.dataobject.purchasehistory;

import lombok.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 供货商供应记录 DO
 *
 * @author 芋道源码
 */
@TableName("pro_purchase_history")
@KeySequence("pro_purchase_history_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseHistoryDO extends BaseDO {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 供应记录ID-即供货单号
     */
    private String purchaseHistoryId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 供应商id
     */
    private String supplyInfoId;
    /**
     * 供货商
     */
    private String supplyCompany;
    /**
     * 产品编号
     */
    private String productSerial;
    /**
     * HS编号
     */
    private String hsSerial;
    /**
     * 产品-尺寸
     */
    private String productSize;
    /**
     * 产品-颜色
     */
    private String productColor;
    /**
     * 产品-克重
     */
    private String productG;
    /**
     * 箱规-长
     */
    private BigDecimal boxLength;
    /**
     * 箱规-宽
     */
    private BigDecimal boxWide;
    /**
     * 箱规-高
     */
    private BigDecimal boxHeight;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 毛重
     */
    private String grossWeight;
    /**
     * 净重
     */
    private String netWeight;
    /**
     * "备注"
     */
    private String remark;
    /**
     * "数量"
     */
    private BigDecimal mount;
    /**
     * "总价"
     */
    private BigDecimal price;
    /**
     * 打包方式
     */
    private String packingWay;
    /**
     * 商品ID
     */
    private String productId;
}
