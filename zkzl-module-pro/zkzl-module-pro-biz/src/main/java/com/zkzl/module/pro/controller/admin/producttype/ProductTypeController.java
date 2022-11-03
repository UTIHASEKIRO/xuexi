package com.zkzl.module.pro.controller.admin.producttype;

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

import com.zkzl.module.pro.controller.admin.producttype.vo.*;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;
import com.zkzl.module.pro.convert.producttype.ProductTypeConvert;
import com.zkzl.module.pro.service.producttype.ProductTypeService;

@Api(tags = "管理后台 - 商品类别")
@RestController
@RequestMapping("/pro/duct-type")
@Validated
public class ProductTypeController {

    @Resource
    private ProductTypeService ductTypeService;

    @PostMapping("/create")
    @ApiOperation("创建商品类别")
    @PreAuthorize("@ss.hasPermission('pro:duct-type:create')")
    public CommonResult<String> createductType(@Valid @RequestBody ProductTypeCreateReqVO createReqVO) {
        return success(ductTypeService.createductType(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新商品类别")
    @PreAuthorize("@ss.hasPermission('pro:duct-type:update')")
    public CommonResult<Boolean> updateductType(@Valid @RequestBody ProductTypeUpdateReqVO updateReqVO) {
        ductTypeService.updateductType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除商品类别")
    @PreAuthorize("@ss.hasPermission('pro:duct-type:delete')")
    public CommonResult<Boolean> deleteductType(@RequestParam("typeId") String typeId) {
        ductTypeService.deleteductType(typeId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得商品类别")
    @PreAuthorize("@ss.hasPermission('pro:duct-type:query')")
    public CommonResult<ProductTypeRespVO> getductType(@RequestParam("typeId") String typeId) {
        ProductTypeDO ductType = ductTypeService.getductType(typeId);
        return success(ProductTypeConvert.INSTANCE.convert(ductType));
    }

    @GetMapping("/list")
    @ApiOperation("获得商品类别列表")
    @PreAuthorize("@ss.hasPermission('pro:duct-type:query')")
    public CommonResult<List<ProductTypeDO>> getductTypeList(@RequestParam("typeName") String typeName) {
        return success(ductTypeService.getductTypeList(typeName));
    }

}
