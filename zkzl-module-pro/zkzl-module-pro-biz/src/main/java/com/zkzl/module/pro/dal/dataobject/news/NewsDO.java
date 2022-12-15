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
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 概要
     */
    private String summary;
    /**
     * 1公司新闻2行业新闻3通知通告
     */
    private Integer newsType;
    /**
     * 是否上架 1上架0下架
     */
    private String shelves;

}
