package com.zkzl.module.pro.dal.dataobject.cususer;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.security.Timestamp;

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;
import static com.zkzl.framework.common.util.date.DateUtils.TIME_ZONE_DEFAULT;

/**
 * 客户公司信息 DO
 *
 * @author 芋道源码
 */
@TableName("pro_cus_user")
@KeySequence("pro_cus_user_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CusUserDO implements Serializable {

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
    /**
     * 租户
     */
    private Integer tenantId;

    /**
     * 营业执照
     */
    private String businessLicense;


    @TableField(exist = false)
    private String mobile;

    @TableField(exist = false)
    private Integer status;

    @TableField(exist = false)
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    @JsonFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND, timezone = TIME_ZONE_DEFAULT)
    private String createTime;
}
