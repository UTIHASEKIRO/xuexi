package com.zkzl.module.pro.convert.news;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.news.vo.*;
import com.zkzl.module.pro.dal.dataobject.news.NewsDO;

/**
 * 新闻 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface NewsConvert {

    NewsConvert INSTANCE = Mappers.getMapper(NewsConvert.class);

    NewsDO convert(NewsCreateReqVO bean);

    NewsDO convert(NewsUpdateReqVO bean);

    NewsRespVO convert(NewsDO bean);

    List<NewsRespVO> convertList(List<NewsDO> list);

    PageResult<NewsRespVO> convertPage(PageResult<NewsDO> page);

    List<NewsExcelVO> convertList02(List<NewsDO> list);

}
