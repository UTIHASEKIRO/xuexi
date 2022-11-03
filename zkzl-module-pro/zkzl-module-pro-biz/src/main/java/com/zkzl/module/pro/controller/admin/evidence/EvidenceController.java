package com.zkzl.module.pro.controller.admin.evidence;

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

import com.zkzl.module.pro.controller.admin.evidence.vo.*;
import com.zkzl.module.pro.dal.dataobject.evidence.EvidenceDO;
import com.zkzl.module.pro.convert.evidence.EvidenceConvert;
import com.zkzl.module.pro.service.evidence.EvidenceService;

@Api(tags = "管理后台 - 订单凭证信息")
@RestController
@RequestMapping("/pro/evidence")
@Validated
public class EvidenceController {

    @Resource
    private EvidenceService evidenceService;

    @PostMapping("/create")
    @ApiOperation("上传定金截图")
    @PreAuthorize("@ss.hasPermission('pro:evidence:create')")
    public CommonResult<Long> createEvidence(@Valid @RequestBody EvidenceCreateReqVO createReqVO) {
        return success(evidenceService.createEvidence(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("上传尾款截图")
    @PreAuthorize("@ss.hasPermission('pro:evidence:update')")
    public CommonResult<Boolean> updateEvidence(@Valid @RequestBody EvidenceUpdateReqVO updateReqVO) {
        evidenceService.updateEvidence(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除订单凭证信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:evidence:delete')")
    public CommonResult<Boolean> deleteEvidence(@RequestParam("id") Long id) {
        evidenceService.deleteEvidence(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得订单凭证信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:evidence:query')")
    public CommonResult<EvidenceRespVO> getEvidence(@RequestParam("id") Long id) {
        EvidenceDO evidence = evidenceService.getEvidence(id);
        return success(EvidenceConvert.INSTANCE.convert(evidence));
    }

    @GetMapping("/list")
    @ApiOperation("获得订单凭证信息列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:evidence:query')")
    public CommonResult<List<EvidenceRespVO>> getEvidenceList(@RequestParam("ids") Collection<Long> ids) {
        List<EvidenceDO> list = evidenceService.getEvidenceList(ids);
        return success(EvidenceConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得订单凭证信息分页")
    @PreAuthorize("@ss.hasPermission('pro:evidence:query')")
    public CommonResult<PageResult<EvidenceRespVO>> getEvidencePage(@Valid EvidencePageReqVO pageVO) {
        PageResult<EvidenceDO> pageResult = evidenceService.getEvidencePage(pageVO);
        return success(EvidenceConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出订单凭证信息 Excel")
    @PreAuthorize("@ss.hasPermission('pro:evidence:export')")
    @OperateLog(type = EXPORT)
    public void exportEvidenceExcel(@Valid EvidenceExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EvidenceDO> list = evidenceService.getEvidenceList(exportReqVO);
        // 导出 Excel
        List<EvidenceExcelVO> datas = EvidenceConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "订单凭证信息.xls", "数据", EvidenceExcelVO.class, datas);
    }

}
