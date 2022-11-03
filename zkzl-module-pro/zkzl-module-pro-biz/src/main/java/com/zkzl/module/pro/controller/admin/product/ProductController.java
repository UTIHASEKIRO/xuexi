package com.zkzl.module.pro.controller.admin.product;

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

import com.zkzl.module.pro.controller.admin.product.vo.*;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import com.zkzl.module.pro.convert.product.ProductConvert;
import com.zkzl.module.pro.service.product.ProductService;

@Api(tags = "管理后台 - 产品")
@RestController
@RequestMapping("/pro/duct")
@Validated
public class ProductController {

    @Resource
    private ProductService ductService;

    @PostMapping("/create")
    @ApiOperation("创建产品")
    @PreAuthorize("@ss.hasPermission('pro:duct:create')")
    public CommonResult<Long> createduct(@Valid @RequestBody ProductCreateReqVO createReqVO) {
        return success(ductService.createduct(createReqVO));
    }

    @GetMapping("/page")
    @ApiOperation("获得产品分页")
    @PreAuthorize("@ss.hasPermission('pro:duct:query')")
    public CommonResult<PageResult<ProductRespVO>> getProductPage(@Valid ProductPageReqVO pageVO) {
        PageResult<ProductRespVO> pageResult = ductService.getductPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/get")
    @ApiOperation("获得产品")
    @PreAuthorize("@ss.hasPermission('pro:duct:query')")
    public CommonResult<ProductRespVO> getProduct(@RequestParam("productId") String productId) {
        ProductRespVO duct = ductService.getProduct(productId);
        return success(duct);
    }

    @PutMapping("/update")
    @ApiOperation("更新产品")
    @PreAuthorize("@ss.hasPermission('pro:duct:update')")
    public CommonResult<Boolean> updateduct(@Valid @RequestBody ProductUpdateReqVO updateReqVO) {
        ductService.updateduct(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除产品")
    @PreAuthorize("@ss.hasPermission('pro:duct:delete')")
    public CommonResult<Boolean> deleteduct(@RequestParam("productId") String productId) {
        ductService.deleteduct(productId);
        return success(true);
    }
}
