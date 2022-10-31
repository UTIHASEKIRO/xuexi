package com.zkzl.module.pro.dal.dataobject.order;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单 DO
 *
 * @author 芋道源码
 */
@TableName("pro_order")
@KeySequence("pro_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDO extends BaseDO {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 卖方公司名称
     */
    private String sellerCompanyName;
    /**
     * 卖方联系地址
     */
    private String sellerCompanyAddress;
    /**
     * 卖方联系人
     */
    private String sellerContact;
    /**
     * 卖方联系电话
     */
    private String sellerTel;
    /**
     * 客户id
     */
    private Long userId;
    /**
     * 总价格
     */
    private BigDecimal price;
    /**
     * 折扣
     */
    private BigDecimal discount;
    /**
     * 合计
     */
    private BigDecimal total;
    /**
     * 报价日期
     */
    private Date priceDate;
    /**
     * 报价有效日期
     */
    private Date effectiveDate;
    /**
     * 说明
     */
    private String remarks;
    /**
     * 1待生产-2生产完成-3待装柜-4待开船-5待到港-6最后完成
     */
    private String status;

}
