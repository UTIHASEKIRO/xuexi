package com.zkzl.module.pro.dal.dataobject.productparameters;

import lombok.*;

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品参数 DO
 *
 * @author 芋道源码
 */
@TableName("pro_product_parameters")
@KeySequence("pro_product_parameters_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductParametersDO implements Serializable {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 参数id
     */
    private String productId;
    /**
     * 父参数id
     */
    private Long parentParamId;
    /**
     * 中文参数名
     */
    private String parameterCn;
    /**
     * 英文参数名
     */
    private String parameterEn;
    /**
     * 中文值
     */
    private String valueCn;
    /**
     * 英文值
     */
    private String valueEn;

    @TableField(exist = false)
    private List<ProductParametersDO> parametersDOS;
}
