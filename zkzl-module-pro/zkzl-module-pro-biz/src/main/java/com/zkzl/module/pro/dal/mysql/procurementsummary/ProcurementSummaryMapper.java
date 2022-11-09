package com.zkzl.module.pro.dal.mysql.procurementsummary;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.procurementsummary.ProcurementSummaryDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.procurementsummary.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 采购汇总 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProcurementSummaryMapper extends BaseMapperX<ProcurementSummaryDO> {

    default PageResult<ProcurementSummaryDO> selectPage(ProcurementSummaryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProcurementSummaryDO>()
                .orderByDesc(ProcurementSummaryDO::getId));
    }

    default List<ProcurementSummaryDO> selectList(ProcurementSummaryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProcurementSummaryDO>()
                .eqIfPresent(ProcurementSummaryDO::getProcurementSummaryId, reqVO.getProcurementSummaryId())
                .eqIfPresent(ProcurementSummaryDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(ProcurementSummaryDO::getOrderChildId, reqVO.getOrderChildId())
                .eqIfPresent(ProcurementSummaryDO::getSupplyCompany, reqVO.getSupplyCompany())
                .eqIfPresent(ProcurementSummaryDO::getFinishPercent, reqVO.getFinishPercent())
                .eqIfPresent(ProcurementSummaryDO::getBuyer, reqVO.getBuyer())
                .eqIfPresent(ProcurementSummaryDO::getProcurementAmount, reqVO.getProcurementAmount())
                .eqIfPresent(ProcurementSummaryDO::getDepositAmount, reqVO.getDepositAmount())
                .eqIfPresent(ProcurementSummaryDO::getBalanceAmount, reqVO.getBalanceAmount())
                .betweenIfPresent(ProcurementSummaryDO::getProduceDate, reqVO.getProduceDate())
                .betweenIfPresent(ProcurementSummaryDO::getConfirmDate, reqVO.getConfirmDate())
                .betweenIfPresent(ProcurementSummaryDO::getEndDate, reqVO.getEndDate())
                .eqIfPresent(ProcurementSummaryDO::getResult, reqVO.getResult())
                .eqIfPresent(ProcurementSummaryDO::getVolume, reqVO.getVolume())
                .eqIfPresent(ProcurementSummaryDO::getFreight, reqVO.getFreight())
                .betweenIfPresent(ProcurementSummaryDO::getDeleveryTime, reqVO.getDeleveryTime())
                .betweenIfPresent(ProcurementSummaryDO::getPackTime, reqVO.getPackTime())
                .eqIfPresent(ProcurementSummaryDO::getRemarks, reqVO.getRemarks())
                .orderByDesc(ProcurementSummaryDO::getId));
    }

    Page<ProcurementSummaryRespVO> getcurementSummaryPage(Page<ProcurementSummaryRespVO> page, @Param("pageReqVO") ProcurementSummaryPageReqVO pageReqVO);

    ProcurementSummaryRespVO getcurementSummary(String procurementSummaryId);

    ProcurementSummaryStatisticsVO getcurementSummaryStatistics();
}
