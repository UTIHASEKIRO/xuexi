package com.zkzl.module.pro.service.procurementsummary;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.procurementsummary.vo.*;
import com.zkzl.module.pro.dal.dataobject.procurementsummary.ProcurementSummaryDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 采购汇总 Service 接口
 *
 * @author 芋道源码
 */
public interface ProcurementSummaryService {

    /**
     * 创建采购汇总
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createcurementSummary(@Valid ProcurementSummaryCreateReqVO createReqVO);

    /**
     * 更新采购汇总
     *
     * @param updateReqVO 更新信息
     */
    void updatecurementSummary(@Valid ProcurementSummaryUpdateReqVO updateReqVO);

    /**
     * 删除采购汇总
     *
     * @param procurementSummaryId 编号
     */
    void deletecurementSummary(String procurementSummaryId);

    /**
     * 获得采购汇总
     *
     * @param procurementSummaryId 编号
     * @return 采购汇总
     */
    ProcurementSummaryRespVO getcurementSummary(String procurementSummaryId);

    /**
     * 获得采购汇总列表
     *
     * @param ids 编号
     * @return 采购汇总列表
     */
    List<ProcurementSummaryDO> getcurementSummaryList(Collection<Long> ids);

    /**
     * 获得采购汇总分页
     *
     * @param pageReqVO 分页查询
     * @return 采购汇总分页
     */
    PageResult<ProcurementSummaryRespVO> getcurementSummaryPage(ProcurementSummaryPageReqVO pageReqVO);

    /**
     * 获得采购汇总列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 采购汇总列表
     */
    List<ProcurementSummaryDO> getcurementSummaryList(ProcurementSummaryExportReqVO exportReqVO);

    ProcurementSummaryStatisticsVO getcurementSummaryStatistics();
}
