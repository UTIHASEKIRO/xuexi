package com.zkzl.module.pro.dal.mysql.partners;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.partners.PartnersDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.partners.vo.*;

/**
 * 合作伙伴 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PartnersMapper extends BaseMapperX<PartnersDO> {

    default PageResult<PartnersDO> selectPage(PartnersPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PartnersDO>()
                .eqIfPresent(PartnersDO::getPicUrl, reqVO.getPicUrl())
                .likeIfPresent(PartnersDO::getName, reqVO.getName())
                .eqIfPresent(PartnersDO::getIntroduce, reqVO.getIntroduce())
                .betweenIfPresent(PartnersDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PartnersDO::getId));
    }

    default List<PartnersDO> selectList(PartnersExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PartnersDO>()
                .eqIfPresent(PartnersDO::getPicUrl, reqVO.getPicUrl())
                .likeIfPresent(PartnersDO::getName, reqVO.getName())
                .eqIfPresent(PartnersDO::getIntroduce, reqVO.getIntroduce())
                .betweenIfPresent(PartnersDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PartnersDO::getId));
    }

}
