package com.zkzl.module.pro.controller.admin.productparameters;

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

import com.zkzl.module.pro.controller.admin.productparameters.vo.*;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import com.zkzl.module.pro.convert.productparameters.ProductParametersConvert;
import com.zkzl.module.pro.service.productparameters.ProductParametersService;

@Api(tags = "管理后台 - 产品参数")
@RestController
@RequestMapping("/pro/duct-parameters")
@Validated
public class ProductParametersController {

    @Resource
    private ProductParametersService ductParametersService;

    @PostMapping("/create")
    @ApiOperation("创建产品参数")
    @PreAuthorize("@ss.hasPermission('pro:duct-parameters:create')")
    public CommonResult<Long> createductParameters(@Valid @RequestBody ProductParametersCreateReqVO createReqVO) {
        return success(ductParametersService.createductParameters(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新产品参数")
    @PreAuthorize("@ss.hasPermission('pro:duct-parameters:update')")
    public CommonResult<Boolean> updateductParameters(@Valid @RequestBody ProductParametersUpdateReqVO updateReqVO) {
        ductParametersService.updateductParameters(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除产品参数")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-parameters:delete')")
    public CommonResult<Boolean> deleteductParameters(@RequestParam("id") Long id) {
        ductParametersService.deleteductParameters(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得产品参数")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-parameters:query')")
    public CommonResult<ProductParametersRespVO> getductParameters(@RequestParam("id") Long id) {
        ProductParametersDO ductParameters = ductParametersService.getductParameters(id);
        return success(ProductParametersConvert.INSTANCE.convert(ductParameters));
    }

    @GetMapping("/list")
    @ApiOperation("获得产品参数列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-parameters:query')")
    public CommonResult<List<ProductParametersRespVO>> getductParametersList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductParametersDO> list = ductParametersService.getductParametersList(ids);
        return success(ProductParametersConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得产品参数分页")
    @PreAuthorize("@ss.hasPermission('pro:duct-parameters:query')")
    public CommonResult<PageResult<ProductParametersRespVO>> getductParametersPage(@Valid ProductParametersPageReqVO pageVO) {
        PageResult<ProductParametersDO> pageResult = ductParametersService.getductParametersPage(pageVO);
        return success(ProductParametersConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出产品参数 Excel")
    @PreAuthorize("@ss.hasPermission('pro:duct-parameters:export')")
    @OperateLog(type = EXPORT)
    public void exportductParametersExcel(@Valid ProductParametersExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductParametersDO> list = ductParametersService.getductParametersList(exportReqVO);
        // 导出 Excel
        List<ProductParametersExcelVO> datas = ProductParametersConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品参数.xls", "数据", ProductParametersExcelVO.class, datas);
    }

}
