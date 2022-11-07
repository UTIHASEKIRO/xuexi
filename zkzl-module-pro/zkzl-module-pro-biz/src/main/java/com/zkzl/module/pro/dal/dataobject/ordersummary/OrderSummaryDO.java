package com.zkzl.module.pro.dal.dataobject.ordersummary;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单汇总 DO
 *
 * @author 芋道源码
 */
@TableName("pro_order_summary")
@KeySequence("pro_order_summary_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummaryDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 订单汇总表id
     */
    private String orderSummaryId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 客户
     */
    private String customer;
    /**
     * 完成进度
     */
    private Integer finishPercent;
    /**
     * 业务员
     */
    private String salesman;
    /**
     * 合同金额
     */
    private BigDecimal contractAmount;
    /**
     * 定金金额
     */
    private BigDecimal depositAmount;
    /**
     * 尾款金额
     */
    private BigDecimal balanceAmount;
    /**
     * 生产进度-生产日期
     */
    private Date produceDate;
    /**
     * 生产进度-印刷包装确认日期
     */
    private Date confirmDate;
    /**
     * 生产进度-结束日期
     */
    private Date endDate;
    /**
     * 产品测试结果汇报
     */
    private String result;
    /**
     * 货代-体积
     */
    private String volume;
    /**
     * 货代-装柜时间
     */
    private Date packTime;
    /**
     * 货代-发货时间
     */
    private Date deleveryTime;
    /**
     * 结束时间
     */
    private Date endingTime;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 状态 1待生产-2生产完成-3待装柜-4待开船-5待到港-6最后完成
     */
    private String status;


}
