package com.zkzl.module.test.dal.dataobject.generate;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 测试自动代码生成 DO
 *
 * @author 芋道源码
 */
@TableName("test_generate")
@KeySequence("test_generate_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateDO extends BaseDO {

    /**
     * 参数主键
     */
    @TableId
    private Integer id;

    private String content;
}
