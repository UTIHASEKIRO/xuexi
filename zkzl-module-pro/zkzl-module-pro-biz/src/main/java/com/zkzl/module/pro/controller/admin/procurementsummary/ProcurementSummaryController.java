package com.zkzl.module.pro.controller.admin.procurementsummary;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.common.pojo.CommonResult;
import static com.zkzl.framework.common.pojo.CommonResult.success;

import com.zkzl.framework.excel.core.util.ExcelUtils;

import com.zkzl.framework.operatelog.core.annotations.OperateLog;
import static com.zkzl.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.zkzl.module.pro.controller.admin.procurementsummary.vo.*;
import com.zkzl.module.pro.dal.dataobject.procurementsummary.ProcurementSummaryDO;
import com.zkzl.module.pro.convert.procurementsummary.ProcurementSummaryConvert;
import com.zkzl.module.pro.service.procurementsummary.ProcurementSummaryService;

@Api(tags = "管理后台 - 采购汇总")
@RestController
@RequestMapping("/pro/curement-summary")
@Validated
public class ProcurementSummaryController {

    @Resource
    private ProcurementSummaryService curementSummaryService;


    @PutMapping("/update")
    @ApiOperation("更新采购汇总")
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:update')")
    public CommonResult<Boolean> updatecurementSummary(@Valid @RequestBody ProcurementSummaryUpdateReqVO updateReqVO) {
        curementSummaryService.updatecurementSummary(updateReqVO);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得采购汇总")
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:query')")
    public CommonResult<ProcurementSummaryRespVO> getcurementSummary(@RequestParam("procurementSummaryId") String procurementSummaryId) {
        ProcurementSummaryRespVO procurementSummaryDO = curementSummaryService.getcurementSummary(procurementSummaryId);
        return success(procurementSummaryDO);
    }

    @GetMapping("/page")
    @ApiOperation("获得采购汇总分页")
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:query')")
    public CommonResult<PageResult<ProcurementSummaryRespVO>> getcurementSummaryPage(@Valid ProcurementSummaryPageReqVO pageVO) {
        PageResult<ProcurementSummaryRespVO> pageResult = curementSummaryService.getcurementSummaryPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/statistics")
    @ApiOperation("采购汇总统计")
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:query')")
    public CommonResult<ProcurementSummaryStatisticsVO> getcurementSummaryStatistics() {
        ProcurementSummaryStatisticsVO statistics = curementSummaryService.getcurementSummaryStatistics();
        return success(statistics);
    }

}
