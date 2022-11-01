package com.zkzl.module.pro.dal.dataobject.customermanage;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户管理跟进 DO
 *
 * @author 芋道源码
 */
@TableName("pro_customer_manage")
@KeySequence("pro_customer_manage_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerManageDO extends BaseDO {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 客户
     */
    private String customerName;
    /**
     * 联系方式
     */
    private String mobile;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 重要等级 1重要2一般3其它
     */
    private String importantLevel;
    /**
     * 客户意向  1高2中3低
     */
    private String customerIntention;
    /**
     * 客户需求，引号;分隔开
     */
    private String customerDemand;
    /**
     * 客户跟进结果 1进行中2已签约3已放弃
     */
    private String customerResult;
    /**
     * 备注
     */
    private String remark;
    /**
     * 跟进次数
     */
    private Integer followNum;
    /**
     * 跟进销售员
     */
    private String followUser;

}
