package com.zkzl.module.pro.dal.dataobject.productpic;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 产品图片 DO
 *
 * @author 芋道源码
 */
@TableName("pro_product_pic")
@KeySequence("pro_product_pic_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPicDO extends BaseDO {

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
     * 图片地址
     */
    private String url;

}
