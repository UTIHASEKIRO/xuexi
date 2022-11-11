package com.zkzl.module.pro.dal.mysql.slidesshow;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.slidesshow.SlidesShowDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.slidesshow.vo.*;

/**
 * 轮播图 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface SlidesShowMapper extends BaseMapperX<SlidesShowDO> {

    default PageResult<SlidesShowDO> selectPage(SlidesShowPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SlidesShowDO>()
                .eqIfPresent(SlidesShowDO::getPicUrl, reqVO.getPicUrl())
                .betweenIfPresent(SlidesShowDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SlidesShowDO::getId));
    }

    default List<SlidesShowDO> selectList(SlidesShowExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SlidesShowDO>()
                .eqIfPresent(SlidesShowDO::getPicUrl, reqVO.getPicUrl())
                .betweenIfPresent(SlidesShowDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SlidesShowDO::getId));
    }

}
