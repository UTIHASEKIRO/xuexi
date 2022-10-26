package com.zkzl.module.market.convert.banner;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.module.market.controller.admin.banner.vo.BannerCreateReqVO;
import com.zkzl.module.market.controller.admin.banner.vo.BannerRespVO;
import com.zkzl.module.market.controller.admin.banner.vo.BannerUpdateReqVO;
import com.zkzl.module.market.dal.dataobject.banner.BannerDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Banner Convert
 *
 * @author xia
 */
@Mapper
public interface BannerConvert {

    BannerConvert INSTANCE = Mappers.getMapper(BannerConvert.class);


    List<BannerRespVO> convertList(List<BannerDO> list);

    PageResult<BannerRespVO> convertPage(PageResult<BannerDO> pageResult);

    BannerRespVO convert(BannerDO banner);

    BannerDO convert(BannerCreateReqVO createReqVO);

    BannerDO convert(BannerUpdateReqVO updateReqVO);

}
