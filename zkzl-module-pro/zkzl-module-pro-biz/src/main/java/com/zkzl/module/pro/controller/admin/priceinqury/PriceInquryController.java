package com.zkzl.module.pro.controller.admin.priceinqury;

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

import com.zkzl.module.pro.controller.admin.priceinqury.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqury.PriceInquryDO;
import com.zkzl.module.pro.convert.priceinqury.PriceInquryConvert;
import com.zkzl.module.pro.service.priceinqury.PriceInquryService;

@Api(tags = "管理后台 - 询价")
@RestController
@RequestMapping("/pro/price-inqury")
@Validated
public class PriceInquryController {

    @Resource
    private PriceInquryService priceInquryService;

    @PostMapping("/create")
    @ApiOperation("创建询价")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:create')")
    public CommonResult<Long> createPriceInqury(@Valid @RequestBody PriceInquryCreateReqVO createReqVO) {
        return success(priceInquryService.createPriceInqury(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新询价")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:update')")
    public CommonResult<Boolean> updatePriceInqury(@Valid @RequestBody PriceInquryUpdateReqVO updateReqVO) {
        priceInquryService.updatePriceInqury(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除询价")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:delete')")
    public CommonResult<Boolean> deletePriceInqury(@RequestParam("id") Long id) {
        priceInquryService.deletePriceInqury(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得询价")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:query')")
    public CommonResult<PriceInquryRespVO> getPriceInqury(@RequestParam("id") Long id) {
        PriceInquryDO priceInqury = priceInquryService.getPriceInqury(id);
        return success(PriceInquryConvert.INSTANCE.convert(priceInqury));
    }

    @GetMapping("/list")
    @ApiOperation("获得询价列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:query')")
    public CommonResult<List<PriceInquryRespVO>> getPriceInquryList(@RequestParam("ids") Collection<Long> ids) {
        List<PriceInquryDO> list = priceInquryService.getPriceInquryList(ids);
        return success(PriceInquryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得询价分页")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:query')")
    public CommonResult<PageResult<PriceInquryRespVO>> getPriceInquryPage(@Valid PriceInquryPageReqVO pageVO) {
        PageResult<PriceInquryDO> pageResult = priceInquryService.getPriceInquryPage(pageVO);
        return success(PriceInquryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出询价 Excel")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:export')")
    @OperateLog(type = EXPORT)
    public void exportPriceInquryExcel(@Valid PriceInquryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PriceInquryDO> list = priceInquryService.getPriceInquryList(exportReqVO);
        // 导出 Excel
        List<PriceInquryExcelVO> datas = PriceInquryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "询价.xls", "数据", PriceInquryExcelVO.class, datas);
    }

}
