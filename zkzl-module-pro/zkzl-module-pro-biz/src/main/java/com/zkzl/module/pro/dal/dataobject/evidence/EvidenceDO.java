package com.zkzl.module.pro.dal.dataobject.evidence;

import lombok.*;

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 订单凭证信息 DO
 *
 * @author 芋道源码
 */
@TableName("pro_evidence")
@KeySequence("pro_evidence_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvidenceDO implements Serializable {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 凭证id
     */
    private String evidenceId;
    /**
     * 询价单id
     */
    private String priceInquryId;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 定金截图
     */
    private String depositPic;
    /**
     * 尾款截图
     */
    private String balancePic;

    /**
     * 订单状态
     */
    @TableField(exist = false)
    private String status;
}
