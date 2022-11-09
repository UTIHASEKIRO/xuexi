package com.zkzl.module.pro.service.procurementsummary;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.procurementsummary.vo.*;
import com.zkzl.module.pro.dal.dataobject.procurementsummary.ProcurementSummaryDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.procurementsummary.ProcurementSummaryConvert;
import com.zkzl.module.pro.dal.mysql.procurementsummary.ProcurementSummaryMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.CUREMENT_SUMMARY_NOT_EXISTS;

/**
 * 采购汇总 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProcurementSummaryServiceImpl implements ProcurementSummaryService {

    @Resource
    private ProcurementSummaryMapper curementSummaryMapper;

    @Override
    public Long createcurementSummary(ProcurementSummaryCreateReqVO createReqVO) {
        // 插入
        ProcurementSummaryDO curementSummary = ProcurementSummaryConvert.INSTANCE.convert(createReqVO);
        curementSummaryMapper.insert(curementSummary);
        // 返回
        return curementSummary.getId();
    }

    @Override
    public void updatecurementSummary(ProcurementSummaryUpdateReqVO updateReqVO) {
        // 校验存在
        this.validatecurementSummaryExists(updateReqVO.getProcurementSummaryId());
        // 更新
        ProcurementSummaryDO updateObj = ProcurementSummaryConvert.INSTANCE.convert(updateReqVO);
        curementSummaryMapper.update(updateObj,new UpdateWrapper<ProcurementSummaryDO>().lambda()
                .eq(ProcurementSummaryDO::getProcurementSummaryId,updateReqVO.getProcurementSummaryId()));
    }

    @Override
    public void deletecurementSummary(String procurementSummaryId) {
        // 校验存在
        this.validatecurementSummaryExists(procurementSummaryId);
        // 删除
        curementSummaryMapper.delete(new LambdaQueryWrapperX<ProcurementSummaryDO>()
                .eq(ProcurementSummaryDO::getProcurementSummaryId, procurementSummaryId));
    }

    private void validatecurementSummaryExists(String procurementSummaryId) {
        ProcurementSummaryDO procurementSummaryDO = curementSummaryMapper.selectOne(new LambdaQueryWrapperX<ProcurementSummaryDO>()
                .eq(ProcurementSummaryDO::getProcurementSummaryId, procurementSummaryId));
        if (procurementSummaryDO == null) {
            throw exception(CUREMENT_SUMMARY_NOT_EXISTS);
        }
    }

    @Override
    public ProcurementSummaryRespVO getcurementSummary(String procurementSummaryId) {
        return curementSummaryMapper.getcurementSummary(procurementSummaryId);
    }

    @Override
    public List<ProcurementSummaryDO> getcurementSummaryList(Collection<Long> ids) {
        return curementSummaryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProcurementSummaryRespVO> getcurementSummaryPage(ProcurementSummaryPageReqVO pageReqVO) {
        Page<ProcurementSummaryRespVO> page = MyBatisUtils.buildPage(pageReqVO);
        Page<ProcurementSummaryRespVO> procurementSummaryRespVOPage = curementSummaryMapper.getcurementSummaryPage(page, pageReqVO);
        return new PageResult<>(procurementSummaryRespVOPage.getRecords(),procurementSummaryRespVOPage.getTotal());
    }

    @Override
    public List<ProcurementSummaryDO> getcurementSummaryList(ProcurementSummaryExportReqVO exportReqVO) {
        return curementSummaryMapper.selectList(exportReqVO);
    }

    @Override
    public ProcurementSummaryStatisticsVO getcurementSummaryStatistics() {
        return curementSummaryMapper.getcurementSummaryStatistics();
    }

}
