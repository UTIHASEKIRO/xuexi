package com.zkzl.module.pro.controller.admin.productfinalparameters;

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

import com.zkzl.module.pro.controller.admin.productfinalparameters.vo.*;
import com.zkzl.module.pro.dal.dataobject.productfinalparameters.ProductFinalParametersDO;
import com.zkzl.module.pro.convert.productfinalparameters.ProductFinalParametersConvert;
import com.zkzl.module.pro.service.productfinalparameters.ProductFinalParametersService;

@Api(tags = "管理后台 - 产品固定参数")
@RestController
@RequestMapping("/pro/duct-final-parameters")
@Validated
public class ProductFinalParametersController {

    @Resource
    private ProductFinalParametersService ductFinalParametersService;

    @PostMapping("/create")
    @ApiOperation("创建产品固定参数")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:create')")
    public CommonResult<Long> createductFinalParameters(@Valid @RequestBody ProductFinalParametersCreateReqVO createReqVO) {
        return success(ductFinalParametersService.createductFinalParameters(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新产品固定参数")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:update')")
    public CommonResult<Boolean> updateductFinalParameters(@Valid @RequestBody ProductFinalParametersUpdateReqVO updateReqVO) {
        ductFinalParametersService.updateductFinalParameters(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除产品固定参数")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:delete')")
    public CommonResult<Boolean> deleteductFinalParameters(@RequestParam("id") Long id) {
        ductFinalParametersService.deleteductFinalParameters(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得产品固定参数")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:query')")
    public CommonResult<ProductFinalParametersRespVO> getductFinalParameters(@RequestParam("id") Long id) {
        ProductFinalParametersDO ductFinalParameters = ductFinalParametersService.getductFinalParameters(id);
        return success(ProductFinalParametersConvert.INSTANCE.convert(ductFinalParameters));
    }

    @GetMapping("/list")
    @ApiOperation("获得产品固定参数列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:query')")
    public CommonResult<List<ProductFinalParametersRespVO>> getductFinalParametersList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductFinalParametersDO> list = ductFinalParametersService.getductFinalParametersList(ids);
        return success(ProductFinalParametersConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得产品固定参数分页")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:query')")
    public CommonResult<PageResult<ProductFinalParametersRespVO>> getductFinalParametersPage(@Valid ProductFinalParametersPageReqVO pageVO) {
        PageResult<ProductFinalParametersDO> pageResult = ductFinalParametersService.getductFinalParametersPage(pageVO);
        return success(ProductFinalParametersConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出产品固定参数 Excel")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:export')")
    @OperateLog(type = EXPORT)
    public void exportductFinalParametersExcel(@Valid ProductFinalParametersExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductFinalParametersDO> list = ductFinalParametersService.getductFinalParametersList(exportReqVO);
        // 导出 Excel
        List<ProductFinalParametersExcelVO> datas = ProductFinalParametersConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品固定参数.xls", "数据", ProductFinalParametersExcelVO.class, datas);
    }

}
