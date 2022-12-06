package com.zkzl.module.pro.service.news;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.news.vo.*;
import com.zkzl.module.pro.dal.dataobject.news.NewsDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 新闻 Service 接口
 *
 * @author 芋道源码
 */
public interface NewsService {

    /**
     * 创建新闻
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createNews(@Valid NewsCreateReqVO createReqVO);

    /**
     * 更新新闻
     *
     * @param updateReqVO 更新信息
     */
    void updateNews(@Valid NewsUpdateReqVO updateReqVO);

    /**
     * 删除新闻
     *
     * @param id 编号
     */
    void deleteNews(Long id);

    /**
     * 获得新闻
     *
     * @param id 编号
     * @return 新闻
     */
    NewsDO getNews(Long id);

    /**
     * 获得新闻列表
     *
     * @param ids 编号
     * @return 新闻列表
     */
    List<NewsDO> getNewsList(Collection<Long> ids);

    /**
     * 获得新闻分页
     *
     * @param pageReqVO 分页查询
     * @return 新闻分页
     */
    PageResult<NewsDO> getNewsPage(NewsPageReqVO pageReqVO);

    /**
     * 获得新闻列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 新闻列表
     */
    List<NewsDO> getNewsList(NewsExportReqVO exportReqVO);

}
