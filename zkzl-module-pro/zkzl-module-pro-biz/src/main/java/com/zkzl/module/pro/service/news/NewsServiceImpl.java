package com.zkzl.module.pro.service.news;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.news.vo.*;
import com.zkzl.module.pro.dal.dataobject.news.NewsDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.news.NewsConvert;
import com.zkzl.module.pro.dal.mysql.news.NewsMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.NEWS_NOT_EXISTS;

/**
 * 新闻 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class NewsServiceImpl implements NewsService {

    @Resource
    private NewsMapper newsMapper;

    @Override
    public Long createNews(NewsCreateReqVO createReqVO) {
        // 插入
        NewsDO news = NewsConvert.INSTANCE.convert(createReqVO);
        newsMapper.insert(news);
        // 返回
        return news.getId();
    }

    @Override
    public void updateNews(NewsUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateNewsExists(updateReqVO.getId());
        // 更新
        NewsDO updateObj = NewsConvert.INSTANCE.convert(updateReqVO);
        newsMapper.updateById(updateObj);
    }

    @Override
    public void deleteNews(Long id) {
        // 校验存在
        this.validateNewsExists(id);
        // 删除
        newsMapper.deleteById(id);
    }

    private void validateNewsExists(Long id) {
        if (newsMapper.selectById(id) == null) {
            throw exception(NEWS_NOT_EXISTS);
        }
    }

    @Override
    public NewsDO getNews(Long id) {
        return newsMapper.selectById(id);
    }

    @Override
    public List<NewsDO> getNewsList(Collection<Long> ids) {
        return newsMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<NewsDO> getNewsPage(NewsPageReqVO pageReqVO) {
        return newsMapper.selectPageNew(pageReqVO);
    }

    @Override
    public List<NewsDO> getNewsList(NewsExportReqVO exportReqVO) {
        return newsMapper.selectList(exportReqVO);
    }

}
