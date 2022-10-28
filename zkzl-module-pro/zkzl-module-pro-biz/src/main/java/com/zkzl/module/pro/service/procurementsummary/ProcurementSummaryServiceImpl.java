package com.zkzl.module.pro.service.procurementsummary;

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
        this.validatecurementSummaryExists(updateReqVO.getId());
        // 更新
        ProcurementSummaryDO updateObj = ProcurementSummaryConvert.INSTANCE.convert(updateReqVO);
        curementSummaryMapper.updateById(updateObj);
    }

    @Override
    public void deletecurementSummary(Long id) {
        // 校验存在
        this.validatecurementSummaryExists(id);
        // 删除
        curementSummaryMapper.deleteById(id);
    }

    private void validatecurementSummaryExists(Long id) {
        if (curementSummaryMapper.selectById(id) == null) {
            throw exception(CUREMENT_SUMMARY_NOT_EXISTS);
        }
    }

    @Override
    public ProcurementSummaryDO getcurementSummary(Long id) {
        return curementSummaryMapper.selectById(id);
    }

    @Override
    public List<ProcurementSummaryDO> getcurementSummaryList(Collection<Long> ids) {
        return curementSummaryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProcurementSummaryDO> getcurementSummaryPage(ProcurementSummaryPageReqVO pageReqVO) {
        return curementSummaryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProcurementSummaryDO> getcurementSummaryList(ProcurementSummaryExportReqVO exportReqVO) {
        return curementSummaryMapper.selectList(exportReqVO);
    }

}
