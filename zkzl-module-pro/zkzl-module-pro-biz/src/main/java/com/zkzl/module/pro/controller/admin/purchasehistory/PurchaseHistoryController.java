package com.zkzl.module.pro.controller.admin.purchasehistory;

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

import com.zkzl.module.pro.controller.admin.purchasehistory.vo.*;
import com.zkzl.module.pro.dal.dataobject.purchasehistory.PurchaseHistoryDO;
import com.zkzl.module.pro.convert.purchasehistory.PurchaseHistoryConvert;
import com.zkzl.module.pro.service.purchasehistory.PurchaseHistoryService;

@Api(tags = "管理后台 - 供货商供应记录")
@RestController
@RequestMapping("/pro/purchase-history")
@Validated
public class PurchaseHistoryController {

    @Resource
    private PurchaseHistoryService purchaseHistoryService;

    @PostMapping("/create")
    @ApiOperation("创建供货商供应记录")
    @PreAuthorize("@ss.hasPermission('pro:purchase-history:create')")
    public CommonResult<Long> createPurchaseHistory(@Valid @RequestBody PurchaseHistoryCreateReqVO createReqVO) {
        return success(purchaseHistoryService.createPurchaseHistory(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新供货商供应记录")
    @PreAuthorize("@ss.hasPermission('pro:purchase-history:update')")
    public CommonResult<Boolean> updatePurchaseHistory(@Valid @RequestBody PurchaseHistoryUpdateReqVO updateReqVO) {
        purchaseHistoryService.updatePurchaseHistory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除供货商供应记录")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:purchase-history:delete')")
    public CommonResult<Boolean> deletePurchaseHistory(@RequestParam("id") Long id) {
        purchaseHistoryService.deletePurchaseHistory(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得供货商供应记录")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:purchase-history:query')")
    public CommonResult<PurchaseHistoryRespVO> getPurchaseHistory(@RequestParam("id") Long id) {
        PurchaseHistoryDO purchaseHistory = purchaseHistoryService.getPurchaseHistory(id);
        return success(PurchaseHistoryConvert.INSTANCE.convert(purchaseHistory));
    }

    @GetMapping("/list")
    @ApiOperation("获得供货商供应记录列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:purchase-history:query')")
    public CommonResult<List<PurchaseHistoryRespVO>> getPurchaseHistoryList(@RequestParam("ids") Collection<Long> ids) {
        List<PurchaseHistoryDO> list = purchaseHistoryService.getPurchaseHistoryList(ids);
        return success(PurchaseHistoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得供货商供应记录分页")
    @PreAuthorize("@ss.hasPermission('pro:purchase-history:query')")
    public CommonResult<PageResult<PurchaseHistoryRespVO>> getPurchaseHistoryPage(@Valid PurchaseHistoryPageReqVO pageVO) {
        PageResult<PurchaseHistoryDO> pageResult = purchaseHistoryService.getPurchaseHistoryPage(pageVO);
        return success(PurchaseHistoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出供货商供应记录 Excel")
    @PreAuthorize("@ss.hasPermission('pro:purchase-history:export')")
    @OperateLog(type = EXPORT)
    public void exportPurchaseHistoryExcel(@Valid PurchaseHistoryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PurchaseHistoryDO> list = purchaseHistoryService.getPurchaseHistoryList(exportReqVO);
        // 导出 Excel
        List<PurchaseHistoryExcelVO> datas = PurchaseHistoryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "供货商供应记录.xls", "数据", PurchaseHistoryExcelVO.class, datas);
    }

}
