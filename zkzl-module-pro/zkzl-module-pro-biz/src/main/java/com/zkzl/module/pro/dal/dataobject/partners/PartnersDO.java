package com.zkzl.module.pro.dal.dataobject.partners;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 合作伙伴 DO
 *
 * @author 芋道源码
 */
@TableName("pro_partners")
@KeySequence("pro_partners_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartnersDO extends BaseDO {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 图片
     */
    private String picUrl;
    /**
     * 合作伙伴名称
     */
    private String name;
    /**
     * 介绍
     */
    private String introduce;

}
