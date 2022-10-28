package com.zkzl.module.pro.dal.dataobject.productfinalparameters;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品固定参数 DO
 *
 * @author 芋道源码
 */
@TableName("pro_product_final_parameters")
@KeySequence("pro_product_final_parameters_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFinalParametersDO extends BaseDO {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 固定参数id
     */
    private String finalParametersId;
    /**
     * 参数名-中文
     */
    private String parameterCn;
    /**
     * 参数名-英文
     */
    private String parameterEn;
    /**
     * 值-中文
     */
    private String valueCn;
    /**
     * 值-英文
     */
    private String valueEn;

}
