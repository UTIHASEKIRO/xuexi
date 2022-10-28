package com.zkzl.module.pro.dal.mysql.priceinqury;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.priceinqury.PriceInquryDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.priceinqury.vo.*;

/**
 * 询价 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PriceInquryMapper extends BaseMapperX<PriceInquryDO> {

    default PageResult<PriceInquryDO> selectPage(PriceInquryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PriceInquryDO>()
                .eqIfPresent(PriceInquryDO::getPriceInquryId, reqVO.getPriceInquryId())
                .likeIfPresent(PriceInquryDO::getSellerCompanyName, reqVO.getSellerCompanyName())
                .eqIfPresent(PriceInquryDO::getSellerCompanyAddress, reqVO.getSellerCompanyAddress())
                .eqIfPresent(PriceInquryDO::getSellerContact, reqVO.getSellerContact())
                .eqIfPresent(PriceInquryDO::getSellerTel, reqVO.getSellerTel())
                .eqIfPresent(PriceInquryDO::getBuyerCompanyId, reqVO.getBuyerCompanyId())
                .eqIfPresent(PriceInquryDO::getPrice, reqVO.getPrice())
                .eqIfPresent(PriceInquryDO::getDiscount, reqVO.getDiscount())
                .eqIfPresent(PriceInquryDO::getTotal, reqVO.getTotal())
                .betweenIfPresent(PriceInquryDO::getPriceDate, reqVO.getPriceDate())
                .betweenIfPresent(PriceInquryDO::getEffectiveDate, reqVO.getEffectiveDate())
                .eqIfPresent(PriceInquryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PriceInquryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PriceInquryDO::getId));
    }

    default List<PriceInquryDO> selectList(PriceInquryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PriceInquryDO>()
                .eqIfPresent(PriceInquryDO::getPriceInquryId, reqVO.getPriceInquryId())
                .likeIfPresent(PriceInquryDO::getSellerCompanyName, reqVO.getSellerCompanyName())
                .eqIfPresent(PriceInquryDO::getSellerCompanyAddress, reqVO.getSellerCompanyAddress())
                .eqIfPresent(PriceInquryDO::getSellerContact, reqVO.getSellerContact())
                .eqIfPresent(PriceInquryDO::getSellerTel, reqVO.getSellerTel())
                .eqIfPresent(PriceInquryDO::getBuyerCompanyId, reqVO.getBuyerCompanyId())
                .eqIfPresent(PriceInquryDO::getPrice, reqVO.getPrice())
                .eqIfPresent(PriceInquryDO::getDiscount, reqVO.getDiscount())
                .eqIfPresent(PriceInquryDO::getTotal, reqVO.getTotal())
                .betweenIfPresent(PriceInquryDO::getPriceDate, reqVO.getPriceDate())
                .betweenIfPresent(PriceInquryDO::getEffectiveDate, reqVO.getEffectiveDate())
                .eqIfPresent(PriceInquryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PriceInquryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PriceInquryDO::getId));
    }

}
