package com.zkzl.module.pro.dal.mysql.supplyinfo;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.supplyinfo.SupplyInfoDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.supplyinfo.vo.*;

/**
 * 供货商信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface SupplyInfoMapper extends BaseMapperX<SupplyInfoDO> {

    default PageResult<SupplyInfoDO> selectPage(SupplyInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SupplyInfoDO>()
                .likeIfPresent(SupplyInfoDO::getName, reqVO.getName())
                .eqIfPresent(SupplyInfoDO::getAddress, reqVO.getAddress())
                .eqIfPresent(SupplyInfoDO::getContact, reqVO.getContact())
                .eqIfPresent(SupplyInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(SupplyInfoDO::getEmail, reqVO.getEmail())
                .betweenIfPresent(SupplyInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SupplyInfoDO::getProduct, reqVO.getProduct())
                .eqIfPresent(SupplyInfoDO::getSortId, reqVO.getSortId())
                .eqIfPresent(SupplyInfoDO::getReasonPrice, reqVO.getReasonPrice())
                .eqIfPresent(SupplyInfoDO::getReasonQuality, reqVO.getReasonQuality())
                .eqIfPresent(SupplyInfoDO::getReasonService, reqVO.getReasonService())
                .orderByDesc(SupplyInfoDO::getId));
    }

    default List<SupplyInfoDO> selectList(SupplyInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SupplyInfoDO>()
                .likeIfPresent(SupplyInfoDO::getName, reqVO.getName())
                .eqIfPresent(SupplyInfoDO::getAddress, reqVO.getAddress())
                .eqIfPresent(SupplyInfoDO::getContact, reqVO.getContact())
                .eqIfPresent(SupplyInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(SupplyInfoDO::getEmail, reqVO.getEmail())
                .betweenIfPresent(SupplyInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SupplyInfoDO::getProduct, reqVO.getProduct())
                .eqIfPresent(SupplyInfoDO::getSortId, reqVO.getSortId())
                .eqIfPresent(SupplyInfoDO::getReasonPrice, reqVO.getReasonPrice())
                .eqIfPresent(SupplyInfoDO::getReasonQuality, reqVO.getReasonQuality())
                .eqIfPresent(SupplyInfoDO::getReasonService, reqVO.getReasonService())
                .orderByDesc(SupplyInfoDO::getId));
    }

}
