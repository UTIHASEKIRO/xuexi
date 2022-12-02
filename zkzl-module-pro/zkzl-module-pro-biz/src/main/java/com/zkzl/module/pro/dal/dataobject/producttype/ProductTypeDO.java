package com.zkzl.module.pro.dal.dataobject.producttype;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 商品类别 DO
 *
 * @author 芋道源码
 */
@TableName("pro_product_type")
@KeySequence("pro_product_type_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypeDO extends BaseDO {

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 父id
     */
    @ApiModelProperty(value = "父id")
    private String parentId;
    /**
     * 类别业务id
     */
    @ApiModelProperty(value = "类别业务id")
    private String typeId;
    /**
     * 类别名称-中文
     */
    @ApiModelProperty(value = "类别名称-中文")
    private String typeNameCn;
    /**
     * 类别名称-英文
     */
    @ApiModelProperty(value = "类别名称-英文")
    private String typeNameEn;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;

    @TableField(exist = false)
    @ApiModelProperty(value = "子节点")
    private List<ProductTypeDO> productTypeDOS;

}
