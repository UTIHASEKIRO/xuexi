package com.zkzl.module.pro.dal.dataobject.supplyinfo;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 供货商信息 DO
 *
 * @author 芋道源码
 */
@TableName("pro_supply_info")
@KeySequence("pro_supply_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplyInfoDO extends BaseDO {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 公司地址
     */
    private String address;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 电话
     */
    private String phone;
    /**
     * email
     */
    private String email;
    /**
     * 主要产品
     */
    private String product;
    /**
     * 产品类别id
     */
    private String typeId;
    /**
     * 推荐理由-价格
     */
    private String reasonPrice;
    /**
     * 推荐理由-质量
     */
    private String reasonQuality;
    /**
     * 推荐理由-服务
     */
    private String reasonService;
    /**
     * 公司评价
     */
    private String evaluation;

}
