package com.zkzl.module.pro.dal.dataobject.company;

import io.swagger.annotations.ApiModelProperty;
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
     * 公司名称
     */
    private String name;
    /**
     * 公司介绍
     */
    private String introduce;
    /**
     * 业务介绍
     */
    private String businessIntroduce;
    /**
     * 公司地址
     */
    private String address;
    /**
     * 电话
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;

}
