package com.zkzl.module.pro.dal.dataobject.product;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

import javax.validation.constraints.NotNull;

/**
 * 产品 DO
 *
 * @author 芋道源码
 */
@TableName("pro_product")
@KeySequence("pro_product_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 产品类别id
     */
    private String typeId;
    /**
     * 中文产品名称
     */
    private String productNameCn;

    /**
     * 英文产品名称
     */
    private String productNameEn;

    /**
     * 状态
     */
    private String shipped;

}
