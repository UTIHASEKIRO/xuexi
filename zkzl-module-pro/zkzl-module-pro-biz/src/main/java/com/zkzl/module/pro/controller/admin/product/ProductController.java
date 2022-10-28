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

    @PutMapping("/update")
    @ApiOperation("更新产品")
    @PreAuthorize("@ss.hasPermission('pro:duct:update')")
    public CommonResult<Boolean> updateduct(@Valid @RequestBody ProductUpdateReqVO updateReqVO) {
        ductService.updateduct(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除产品")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct:delete')")
    public CommonResult<Boolean> deleteduct(@RequestParam("id") Long id) {
        ductService.deleteduct(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得产品")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct:query')")
    public CommonResult<ProductRespVO> getduct(@RequestParam("id") Long id) {
        ProductDO duct = ductService.getduct(id);
        return success(ProductConvert.INSTANCE.convert(duct));
    }

    @GetMapping("/list")
    @ApiOperation("获得产品列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:duct:query')")
    public CommonResult<List<ProductRespVO>> getductList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductDO> list = ductService.getductList(ids);
        return success(ProductConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得产品分页")
    @PreAuthorize("@ss.hasPermission('pro:duct:query')")
    public CommonResult<PageResult<ProductRespVO>> getductPage(@Valid ProductPageReqVO pageVO) {
        PageResult<ProductDO> pageResult = ductService.getductPage(pageVO);
        return success(ProductConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出产品 Excel")
    @PreAuthorize("@ss.hasPermission('pro:duct:export')")
    @OperateLog(type = EXPORT)
    public void exportductExcel(@Valid ProductExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductDO> list = ductService.getductList(exportReqVO);
        // 导出 Excel
        List<ProductExcelVO> datas = ProductConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品.xls", "数据", ProductExcelVO.class, datas);
    }

}
