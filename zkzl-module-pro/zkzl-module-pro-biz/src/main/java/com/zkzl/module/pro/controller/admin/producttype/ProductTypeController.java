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
    public CommonResult<Long> createductType(@Valid @RequestBody ProductTypeCreateReqVO createReqVO) {
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
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-type:delete')")
    public CommonResult<Boolean> deleteductType(@RequestParam("id") Long id) {
        ductTypeService.deleteductType(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得商品类别")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-type:query')")
    public CommonResult<ProductTypeRespVO> getductType(@RequestParam("id") Long id) {
        ProductTypeDO ductType = ductTypeService.getductType(id);
        return success(ProductTypeConvert.INSTANCE.convert(ductType));
    }

    @GetMapping("/list")
    @ApiOperation("获得商品类别列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-type:query')")
    public CommonResult<List<ProductTypeRespVO>> getductTypeList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductTypeDO> list = ductTypeService.getductTypeList(ids);
        return success(ProductTypeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得商品类别分页")
    @PreAuthorize("@ss.hasPermission('pro:duct-type:query')")
    public CommonResult<PageResult<ProductTypeRespVO>> getductTypePage(@Valid ProductTypePageReqVO pageVO) {
        PageResult<ProductTypeDO> pageResult = ductTypeService.getductTypePage(pageVO);
        return success(ProductTypeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出商品类别 Excel")
    @PreAuthorize("@ss.hasPermission('pro:duct-type:export')")
    @OperateLog(type = EXPORT)
    public void exportductTypeExcel(@Valid ProductTypeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductTypeDO> list = ductTypeService.getductTypeList(exportReqVO);
        // 导出 Excel
        List<ProductTypeExcelVO> datas = ProductTypeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "商品类别.xls", "数据", ProductTypeExcelVO.class, datas);
    }

}
