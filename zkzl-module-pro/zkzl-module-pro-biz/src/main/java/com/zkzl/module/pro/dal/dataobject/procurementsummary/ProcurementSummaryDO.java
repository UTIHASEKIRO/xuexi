package com.zkzl.module.pro.dal.dataobject.procurementsummary;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 采购汇总 DO
 *
 * @author 芋道源码
 */
@TableName("pro_procurement_summary")
@KeySequence("pro_procurement_summary_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcurementSummaryDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 采购汇总表id
     */
    private String procurementSummaryId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 子订单编号
     */
    private String orderChildId;
    /**
     * 供应商名称
     */
    private String supplyCompany;
    /**
     * 完成进度
     */
    private Integer finishPercent;
    /**
     * 采购员
     */
    private String buyer;
    /**
     * 采购金额
     */
    private BigDecimal procurementAmount;
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
     * 物流运输-体积
     */
    private String volume;
    /**
     * 物流运输-运费
     */
    private BigDecimal freight;
    /**
     * 物流运输-发货时间
     */
    private Date deleveryTime;
    /**
     * 装柜时间
     */
    private Date packTime;
    /**
     * 备注
     */
    private String remarks;

    /**
     * 采购单状态（0采购中1已完成）
     */
    private Integer status;

}
