package com.zkzl.module.pro.controller.admin.priceinqurychild;

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

import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqurychild.PriceInquryChildDO;
import com.zkzl.module.pro.convert.priceinqurychild.PriceInquryChildConvert;
import com.zkzl.module.pro.service.priceinqurychild.PriceInquryChildService;

@Api(tags = "管理后台 - 询价表子")
@RestController
@RequestMapping("/pro/price-inqury-child")
@Validated
public class PriceInquryChildController {

    @Resource
    private PriceInquryChildService priceInquryChildService;

    @PostMapping("/create")
    @ApiOperation("创建询价表子")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury-child:create')")
    public CommonResult<Long> createPriceInquryChild(@Valid @RequestBody PriceInquryChildCreateReqVO createReqVO) {
        return success(priceInquryChildService.createPriceInquryChild(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新询价表子")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury-child:update')")
    public CommonResult<Boolean> updatePriceInquryChild(@Valid @RequestBody PriceInquryChildUpdateReqVO updateReqVO) {
        priceInquryChildService.updatePriceInquryChild(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除询价表子")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:price-inqury-child:delete')")
    public CommonResult<Boolean> deletePriceInquryChild(@RequestParam("id") Long id) {
        priceInquryChildService.deletePriceInquryChild(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得询价表子")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:price-inqury-child:query')")
    public CommonResult<PriceInquryChildRespVO> getPriceInquryChild(@RequestParam("id") Long id) {
        PriceInquryChildDO priceInquryChild = priceInquryChildService.getPriceInquryChild(id);
        return success(PriceInquryChildConvert.INSTANCE.convert(priceInquryChild));
    }

    @GetMapping("/list")
    @ApiOperation("获得询价表子列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:price-inqury-child:query')")
    public CommonResult<List<PriceInquryChildRespVO>> getPriceInquryChildList(@RequestParam("ids") Collection<Long> ids) {
        List<PriceInquryChildDO> list = priceInquryChildService.getPriceInquryChildList(ids);
        return success(PriceInquryChildConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得询价表子分页")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury-child:query')")
    public CommonResult<PageResult<PriceInquryChildRespVO>> getPriceInquryChildPage(@Valid PriceInquryChildPageReqVO pageVO) {
        PageResult<PriceInquryChildDO> pageResult = priceInquryChildService.getPriceInquryChildPage(pageVO);
        return success(PriceInquryChildConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出询价表子 Excel")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury-child:export')")
    @OperateLog(type = EXPORT)
    public void exportPriceInquryChildExcel(@Valid PriceInquryChildExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PriceInquryChildDO> list = priceInquryChildService.getPriceInquryChildList(exportReqVO);
        // 导出 Excel
        List<PriceInquryChildExcelVO> datas = PriceInquryChildConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "询价表子.xls", "数据", PriceInquryChildExcelVO.class, datas);
    }

}
