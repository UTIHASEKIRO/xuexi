package com.zkzl.module.market.dal.mysql.activity;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.market.dal.dataobject.activity.ActivityDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.market.controller.admin.activity.vo.*;

/**
 * 促销活动 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ActivityMapper extends BaseMapperX<ActivityDO> {

    default PageResult<ActivityDO> selectPage(ActivityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ActivityDO>()
                .eqIfPresent(ActivityDO::getTitle, reqVO.getTitle())
                .eqIfPresent(ActivityDO::getActivityType, reqVO.getActivityType())
                .eqIfPresent(ActivityDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ActivityDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(ActivityDO::getEndTime, reqVO.getEndTime())
                .betweenIfPresent(ActivityDO::getInvalidTime, reqVO.getInvalidTime())
                .betweenIfPresent(ActivityDO::getDeleteTime, reqVO.getDeleteTime())
                .eqIfPresent(ActivityDO::getTimeLimitedDiscount, reqVO.getTimeLimitedDiscount())
                .eqIfPresent(ActivityDO::getFullPrivilege, reqVO.getFullPrivilege())
                .betweenIfPresent(ActivityDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ActivityDO::getId));
    }

}
