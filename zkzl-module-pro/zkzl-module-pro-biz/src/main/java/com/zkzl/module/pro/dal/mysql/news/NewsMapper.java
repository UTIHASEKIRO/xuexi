package com.zkzl.module.pro.dal.mysql.news;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.news.NewsDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.news.vo.*;

/**
 * 新闻 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface NewsMapper extends BaseMapperX<NewsDO> {

    default PageResult<NewsDO> selectPage(NewsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NewsDO>()
                .eqIfPresent(NewsDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(NewsDO::getTitleCn, reqVO.getTitleCn())
                .eqIfPresent(NewsDO::getTitleEn, reqVO.getTitleEn())
                .eqIfPresent(NewsDO::getSummaryCn, reqVO.getSummaryCn())
                .eqIfPresent(NewsDO::getSummaryEn, reqVO.getSummaryEn())
                .eqIfPresent(NewsDO::getContentCn, reqVO.getContentCn())
                .eqIfPresent(NewsDO::getContentEn, reqVO.getContentEn())
                .eqIfPresent(NewsDO::getNewsType, reqVO.getNewsType())
                .eqIfPresent(NewsDO::getShelves, reqVO.getShelves())
                .betweenIfPresent(NewsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(NewsDO::getId));
    }

    default PageResult<NewsDO> selectPageNew(NewsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NewsDO>()
                .likeIfPresent(NewsDO::getTitleCn,reqVO.getTitleCn())
                .orX()
                .likeIfPresent(NewsDO::getTitleEn, reqVO.getTitleCn())
                .likeIfPresent(NewsDO::getSummaryCn, reqVO.getSummaryCn())
                .orX()
                .likeIfPresent(NewsDO::getSummaryEn, reqVO.getSummaryCn())
                .eqIfPresent(NewsDO::getNewsType, reqVO.getNewsType())
                .eqIfPresent(NewsDO::getShelves, reqVO.getShelves())
                .betweenIfPresent(NewsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(NewsDO::getId));
    }

    default List<NewsDO> selectList(NewsExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<NewsDO>()
                .eqIfPresent(NewsDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(NewsDO::getTitleCn, reqVO.getTitleCn())
                .eqIfPresent(NewsDO::getTitleEn, reqVO.getTitleEn())
                .eqIfPresent(NewsDO::getSummaryCn, reqVO.getSummaryCn())
                .eqIfPresent(NewsDO::getSummaryEn, reqVO.getSummaryEn())
                .eqIfPresent(NewsDO::getContentCn, reqVO.getContentCn())
                .eqIfPresent(NewsDO::getContentEn, reqVO.getContentEn())
                .eqIfPresent(NewsDO::getNewsType, reqVO.getNewsType())
                .eqIfPresent(NewsDO::getShelves, reqVO.getShelves())
                .betweenIfPresent(NewsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(NewsDO::getId));
    }

}
