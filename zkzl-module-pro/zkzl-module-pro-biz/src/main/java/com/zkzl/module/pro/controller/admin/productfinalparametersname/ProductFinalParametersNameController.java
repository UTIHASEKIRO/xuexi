package com.zkzl.module.pro.controller.admin.productfinalparametersname;

import com.zkzl.module.pro.service.productfinalparametersname.ProductFinalParametersNameService;
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

import com.zkzl.module.pro.controller.admin.productfinalparametersname.vo.*;
import com.zkzl.module.pro.dal.dataobject.productfinalparametersname.ProductFinalParametersNameDO;
import com.zkzl.module.pro.convert.productfinalparametersname.ProductFinalParametersNameConvert;

@Api(tags = "管理后台 - 产品固定参数名")
@RestController
@RequestMapping("/pro/duct-final-parameters-name")
@Validated
public class ProductFinalParametersNameController {

    @Resource
    private ProductFinalParametersNameService ductFinalParametersNameService;

    @PostMapping("/create")
    @ApiOperation("创建产品固定参数名")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters-name:create')")
    public CommonResult<Long> createductFinalParametersName(@Valid @RequestBody ProductFinalParametersNameCreateReqVO createReqVO) {
        return success(ductFinalParametersNameService.createductFinalParametersName(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新产品固定参数名")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters-name:update')")
    public CommonResult<Boolean> updateductFinalParametersName(@Valid @RequestBody ProductFinalParametersNameUpdateReqVO updateReqVO) {
        ductFinalParametersNameService.updateductFinalParametersName(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除产品固定参数名")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters-name:delete')")
    public CommonResult<Boolean> deleteductFinalParametersName(@RequestParam("id") Long id) {
        ductFinalParametersNameService.deleteductFinalParametersName(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得产品固定参数名")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters-name:query')")
    public CommonResult<ProductFinalParametersNameRespVO> getductFinalParametersName(@RequestParam("id") Long id) {
        ProductFinalParametersNameDO ductFinalParametersName = ductFinalParametersNameService.getductFinalParametersName(id);
        return success(ProductFinalParametersNameConvert.INSTANCE.convert(ductFinalParametersName));
    }

    @GetMapping("/list")
    @ApiOperation("获得产品固定参数名列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters-name:query')")
    public CommonResult<List<ProductFinalParametersNameRespVO>> getductFinalParametersNameList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductFinalParametersNameDO> list = ductFinalParametersNameService.getductFinalParametersNameList(ids);
        return success(ProductFinalParametersNameConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得产品固定参数名分页")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters-name:query')")
    public CommonResult<PageResult<ProductFinalParametersNameRespVO>> getductFinalParametersNamePage(@Valid ProductFinalParametersNamePageReqVO pageVO) {
        PageResult<ProductFinalParametersNameDO> pageResult = ductFinalParametersNameService.getductFinalParametersNamePage(pageVO);
        return success(ProductFinalParametersNameConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出产品固定参数名 Excel")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters-name:export')")
    @OperateLog(type = EXPORT)
    public void exportductFinalParametersNameExcel(@Valid ProductFinalParametersNameExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductFinalParametersNameDO> list = ductFinalParametersNameService.getductFinalParametersNameList(exportReqVO);
        // 导出 Excel
        List<ProductFinalParametersNameExcelVO> datas = ProductFinalParametersNameConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品固定参数名.xls", "数据", ProductFinalParametersNameExcelVO.class, datas);
    }

}
