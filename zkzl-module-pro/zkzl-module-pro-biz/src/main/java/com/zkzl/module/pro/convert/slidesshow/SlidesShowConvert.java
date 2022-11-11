package com.zkzl.module.pro.convert.slidesshow;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.slidesshow.vo.*;
import com.zkzl.module.pro.dal.dataobject.slidesshow.SlidesShowDO;

/**
 * 轮播图 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface SlidesShowConvert {

    SlidesShowConvert INSTANCE = Mappers.getMapper(SlidesShowConvert.class);

    SlidesShowDO convert(SlidesShowCreateReqVO bean);

    SlidesShowDO convert(SlidesShowUpdateReqVO bean);

    SlidesShowRespVO convert(SlidesShowDO bean);

    List<SlidesShowRespVO> convertList(List<SlidesShowDO> list);

    PageResult<SlidesShowRespVO> convertPage(PageResult<SlidesShowDO> page);

    List<SlidesShowExcelVO> convertList02(List<SlidesShowDO> list);

}
