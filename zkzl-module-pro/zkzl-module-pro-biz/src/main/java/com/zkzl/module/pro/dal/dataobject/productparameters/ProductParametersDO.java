package com.zkzl.module.pro.dal.dataobject.productparameters;

import lombok.*;
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
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductParametersDO extends BaseDO {

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
     * 参数名
     */
    private String parameter;
    /**
     * 值
     */
    private String value;

}
