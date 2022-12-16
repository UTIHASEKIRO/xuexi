package com.zkzl.module.pro.dal.dataobject.company;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 公司信息维护 DO
 *
 * @author 芋道源码
 */
@TableName("pro_company")
@KeySequence("pro_company_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDO extends BaseDO {

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
     * 电话
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 公司名称_中文
     */
    private String nameCn;
    /**
     * 公司名称_英文
     */
    private String nameEn;
    /**
     * 公司介绍_中文
     */
    private String introduceCn;
    /**
     * 公司介绍_英文
     */
    private String introduceEn;
    /**
     * 业务介绍_中文
     */
    private String businessIntroduceCn;
    /**
     * 业务介绍_英文
     */
    private String businessIntroduceEn;
    /**
     * 公司地址_中文
     */
    private String addressCn;
    /**
     * 公司地址_英文
     */
    private String addressEn;

}
