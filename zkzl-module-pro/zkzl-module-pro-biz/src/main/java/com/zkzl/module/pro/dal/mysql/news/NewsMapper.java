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
                .eqIfPresent(NewsDO::getTitle, reqVO.getTitle())
                .eqIfPresent(NewsDO::getContent, reqVO.getContent())
                .betweenIfPresent(NewsDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(NewsDO::getSummary, reqVO.getSummary())
                .eqIfPresent(NewsDO::getNewsType, reqVO.getNewsType())
                .eqIfPresent(NewsDO::getShelves, reqVO.getShelves())
                .orderByDesc(NewsDO::getId));
    }

    default List<NewsDO> selectList(NewsExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<NewsDO>()
                .eqIfPresent(NewsDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(NewsDO::getTitle, reqVO.getTitle())
                .eqIfPresent(NewsDO::getContent, reqVO.getContent())
                .betweenIfPresent(NewsDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(NewsDO::getSummary, reqVO.getSummary())
                .eqIfPresent(NewsDO::getNewsType, reqVO.getNewsType())
                .eqIfPresent(NewsDO::getShelves, reqVO.getShelves())
                .orderByDesc(NewsDO::getId));
    }

}
