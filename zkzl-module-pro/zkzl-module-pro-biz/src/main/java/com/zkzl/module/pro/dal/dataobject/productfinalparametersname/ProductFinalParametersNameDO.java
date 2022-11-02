package com.zkzl.module.pro.dal.dataobject.productfinalparametersname;

import lombok.*;

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品固定参数名 DO
 *
 * @author 芋道源码
 */
@TableName("pro_product_final_parameters_name")
@KeySequence("pro_product_final_parameters_name_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductFinalParametersNameDO implements Serializable {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 参数名-中文
     */
    private String parameterCn;
    /**
     * 参数名-英文
     */
    private String parameterEn;

}
