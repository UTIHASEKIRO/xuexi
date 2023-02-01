package com.zkzl.module.pro.controller.admin.product;

import com.zkzl.framework.excel.core.util.ExcelUtils;
import com.zkzl.framework.operatelog.core.annotations.OperateLog;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.common.pojo.CommonResult;
import static com.zkzl.framework.common.pojo.CommonResult.success;
import static com.zkzl.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;


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
        PageResult<ProductDO> pageResult = ductService.getductPage(pageVO);
        return success(ProductConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list")
    @ApiOperation("获得产品列表")
    @PreAuthorize("@ss.hasPermission('pro:duct:query')")
    public CommonResult<List<ProductRespVO>> getProductList(String typeId) {
        List<ProductDO> productDOS = ductService.getductList(typeId);
        return success(ProductConvert.INSTANCE.convertList(productDOS));
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

    @GetMapping("/updateShipped")
    @ApiOperation("上架下架")
    @PreAuthorize("@ss.hasPermission('pro:duct:update')")
    public CommonResult<Boolean> updateShipped(@RequestParam("productId") String productId) {
        ductService.updateShipped(productId);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除产品")
    @PreAuthorize("@ss.hasPermission('pro:duct:delete')")
    public CommonResult<Boolean> deleteduct(@RequestParam("productId") String productId) {
        ductService.deleteduct(productId);
        return success(true);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出产品 Excel")
    @PreAuthorize("@ss.hasPermission('pro:duct:export')")
    @OperateLog(type = EXPORT)
    public void exportductExcel(@Valid ProductExportReqVO exportReqVO,
                                HttpServletResponse response) throws IOException {
        List<ProductExcelVO2> list = ductService.getductList2(exportReqVO);
        for (ProductExcelVO2 productExcelVO2 : list) {
            String filePath = ExcelUtils.saveFile(productExcelVO2.getUrl(), ExcelUtils.path);
            productExcelVO2.setPic(new File(filePath));
        }
//        List<ProductExcelVO2> productDOList = list.stream()
//                .filter(ProductExcelVO2 -> ProductExcelVO2.getShipped().equals("0"))
//                .sorted(Comparator.comparing(ProductExcelVO2::getTypeId))
//                .collect(Collectors.toList());

        // 导出 Excel
//        List<ProductExcelVO> datas = ProductConvert.INSTANCE.convertList02(productDOList);
        ExcelUtils.write(response, "产品.xls", "数据", ProductExcelVO2.class, list);
    }
}
