package com.zkzl.module.pro.dal.dataobject.ordercost;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单成本 DO
 *
 * @author admin2
 */
@TableName("pro_order_cost")
@KeySequence("pro_order_cost_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCostDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 订单成本表业务id
     */
    private String orderCostId;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 单价
     */
    private BigDecimal unitPrice;
    /**
     * 数量
     */
    private Integer mount;
    /**
     * 供货商id
     */
    private String supplyInfoId;
    /**
     * 成本价
     */
    private BigDecimal costPrice;
    /**
     * 询价时间
     */
    private Date priceDate;
    /**
     * 开始时间(订单生成时间)
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 供应商名称
     */
    @TableField(exist = false)
    private String supply;

    /**
     * 产品名称
     */
    @TableField(exist = false)
    private String product;

}
