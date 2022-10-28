package com.zkzl.module.pro.dal.dataobject.cususer;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户公司信息 DO
 *
 * @author 芋道源码
 */
@TableName("pro_cus_user")
@KeySequence("pro_cus_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CusUserDO extends BaseDO {

    /**
     * 用户ID
     */
    @TableId
    private Long userId;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 联系人
     */
    private String contectName;

}
