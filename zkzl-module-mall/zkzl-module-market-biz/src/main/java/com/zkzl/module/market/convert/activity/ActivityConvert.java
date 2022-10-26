package com.zkzl.module.market.convert.activity;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.market.controller.admin.activity.vo.*;
import com.zkzl.module.market.dal.dataobject.activity.ActivityDO;

/**
 * 促销活动 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ActivityConvert {

    ActivityConvert INSTANCE = Mappers.getMapper(ActivityConvert.class);

    ActivityDO convert(ActivityCreateReqVO bean);

    ActivityDO convert(ActivityUpdateReqVO bean);

    ActivityRespVO convert(ActivityDO bean);

    List<ActivityRespVO> convertList(List<ActivityDO> list);

    PageResult<ActivityRespVO> convertPage(PageResult<ActivityDO> page);

}
