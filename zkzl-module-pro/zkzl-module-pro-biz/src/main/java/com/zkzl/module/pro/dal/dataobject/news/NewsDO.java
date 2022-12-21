package com.zkzl.module.pro.dal.dataobject.news;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * 新闻 DO
 *
 * @author 芋道源码
 */
@TableName("pro_news")
@KeySequence("pro_news_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsDO extends BaseDO {

    /**
     * 序号id
     */
    @TableId
    private Long id;
    /**
     * 图片
     */
    private String picUrl;
    /**
     * 标题_中文
     */
    private String titleCn;
    /**
     * 标题_英文
     */
    private String titleEn;
    /**
     * 概要_中文
     */
    private String summaryCn;
    /**
     * 概要_英文
     */
    private String summaryEn;
    /**
     * 内容_中文
     */
    private String contentCn;
    /**
     * 内容_英文
     */
    private String contentEn;
    /**
     * 1公司新闻2行业新闻3通知通告
     *
     * 枚举 {@link TODO news_type 对应的类}
     */
    private Integer newsType;
    /**
     * 是否上架 1上架0下架
     *
     * 枚举 {@link TODO is_shelf 对应的类}
     */
    private String shelves;

}
