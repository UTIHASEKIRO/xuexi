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

    @PostMapping("/create")
    @ApiOperation("创建采购汇总")
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:create')")
    public CommonResult<Long> createcurementSummary(@Valid @RequestBody ProcurementSummaryCreateReqVO createReqVO) {
        return success(curementSummaryService.createcurementSummary(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新采购汇总")
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:update')")
    public CommonResult<Boolean> updatecurementSummary(@Valid @RequestBody ProcurementSummaryUpdateReqVO updateReqVO) {
        curementSummaryService.updatecurementSummary(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除采购汇总")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:delete')")
    public CommonResult<Boolean> deletecurementSummary(@RequestParam("id") Long id) {
        curementSummaryService.deletecurementSummary(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得采购汇总")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:query')")
    public CommonResult<ProcurementSummaryRespVO> getcurementSummary(@RequestParam("id") Long id) {
        ProcurementSummaryDO curementSummary = curementSummaryService.getcurementSummary(id);
        return success(ProcurementSummaryConvert.INSTANCE.convert(curementSummary));
    }

    @GetMapping("/list")
    @ApiOperation("获得采购汇总列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:query')")
    public CommonResult<List<ProcurementSummaryRespVO>> getcurementSummaryList(@RequestParam("ids") Collection<Long> ids) {
        List<ProcurementSummaryDO> list = curementSummaryService.getcurementSummaryList(ids);
        return success(ProcurementSummaryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得采购汇总分页")
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:query')")
    public CommonResult<PageResult<ProcurementSummaryRespVO>> getcurementSummaryPage(@Valid ProcurementSummaryPageReqVO pageVO) {
        PageResult<ProcurementSummaryDO> pageResult = curementSummaryService.getcurementSummaryPage(pageVO);
        return success(ProcurementSummaryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出采购汇总 Excel")
    @PreAuthorize("@ss.hasPermission('pro:curement-summary:export')")
    @OperateLog(type = EXPORT)
    public void exportcurementSummaryExcel(@Valid ProcurementSummaryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProcurementSummaryDO> list = curementSummaryService.getcurementSummaryList(exportReqVO);
        // 导出 Excel
        List<ProcurementSummaryExcelVO> datas = ProcurementSummaryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "采购汇总.xls", "数据", ProcurementSummaryExcelVO.class, datas);
    }

}
