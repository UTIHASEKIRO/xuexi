package com.zkzl.module.pro.dal.dataobject.priceinqury;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 询价 DO
 *
 * @author 芋道源码
 */
@TableName("pro_price_inqury")
@KeySequence("pro_price_inqury_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceInquryDO extends BaseDO {

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
    private Long buyerCompanyId;
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
     * 状态 1客户询价状态 2老板确认状态 3询价完成状态
     */
    private String status;

}
