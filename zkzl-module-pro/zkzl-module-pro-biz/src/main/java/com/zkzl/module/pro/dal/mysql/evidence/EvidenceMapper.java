package com.zkzl.module.pro.dal.mysql.evidence;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.evidence.EvidenceDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.evidence.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 订单凭证信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface EvidenceMapper extends BaseMapperX<EvidenceDO> {

    default PageResult<EvidenceDO> selectPage(EvidencePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvidenceDO>()
                .eqIfPresent(EvidenceDO::getEvidenceId, reqVO.getEvidenceId())
                .eqIfPresent(EvidenceDO::getPriceInquryId, reqVO.getPriceInquryId())
                .eqIfPresent(EvidenceDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(EvidenceDO::getDepositPic, reqVO.getDepositPic())
                .eqIfPresent(EvidenceDO::getBalancePic, reqVO.getBalancePic())
                .orderByDesc(EvidenceDO::getId));
    }

    default List<EvidenceDO> selectList(EvidenceExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EvidenceDO>()
                .eqIfPresent(EvidenceDO::getEvidenceId, reqVO.getEvidenceId())
                .eqIfPresent(EvidenceDO::getPriceInquryId, reqVO.getPriceInquryId())
                .eqIfPresent(EvidenceDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(EvidenceDO::getDepositPic, reqVO.getDepositPic())
                .eqIfPresent(EvidenceDO::getBalancePic, reqVO.getBalancePic())
                .orderByDesc(EvidenceDO::getId));
    }

    EvidenceDO getEvidence(Long id);

    IPage<EvidenceDO> getEvidencePage(@Param("mpPage") IPage<EvidenceDO> mpPage, @Param("pageReqVO") EvidencePageReqVO pageReqVO);
}
