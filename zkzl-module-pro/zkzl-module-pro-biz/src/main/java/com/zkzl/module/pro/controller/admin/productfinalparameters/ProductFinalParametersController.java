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

    @GetMapping("/page")
    @ApiOperation("产品固定参数查询")
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:query')")
    public CommonResult<PageResult<ProductFinalParametersRespVO>> getProductFinalParametersPage(@Valid ProductFinalParametersPageReqVO pageVO) {
        PageResult<ProductFinalParametersDO> pageResult = ductFinalParametersService.getductFinalParametersPage(pageVO);
        return success(ProductFinalParametersConvert.INSTANCE.convertPage(pageResult));
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
    @PreAuthorize("@ss.hasPermission('pro:duct-final-parameters:update')")
    public CommonResult<PageResult<ProductFinalParametersRespVO>> getductFinalParametersPage(@Valid ProductFinalParametersPageReqVO pageVO) {
        PageResult<ProductFinalParametersDO> pageResult = ductFinalParametersService.getductFinalParametersPage(pageVO);
        return success(ProductFinalParametersConvert.INSTANCE.convertPage(pageResult));
    }

}
