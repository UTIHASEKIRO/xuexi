package com.zkzl.module.pro.dal.dataobject.productcertificate;

import lombok.*;

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 证书 DO
 *
 * @author 芋道源码
 */
@TableName("pro_product_certificate")
@KeySequence("pro_product_certificate_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCertificateDO implements Serializable {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 证书地址
     */
    private String certificateUrl;

}
