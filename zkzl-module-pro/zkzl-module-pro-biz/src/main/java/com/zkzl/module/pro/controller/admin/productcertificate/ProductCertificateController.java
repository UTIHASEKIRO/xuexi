package com.zkzl.module.pro.controller.admin.productcertificate;

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

import com.zkzl.module.pro.controller.admin.productcertificate.vo.*;
import com.zkzl.module.pro.dal.dataobject.productcertificate.ProductCertificateDO;
import com.zkzl.module.pro.convert.productcertificate.ProductCertificateConvert;
import com.zkzl.module.pro.service.productcertificate.ProductCertificateService;

@Api(tags = "管理后台 - 证书")
@RestController
@RequestMapping("/pro/duct-certificate")
@Validated
public class ProductCertificateController {

    @Resource
    private ProductCertificateService ductCertificateService;

    @PostMapping("/create")
    @ApiOperation("创建证书")
    @PreAuthorize("@ss.hasPermission('pro:duct-certificate:create')")
    public CommonResult<Long> createductCertificate(@Valid @RequestBody ProductCertificateCreateReqVO createReqVO) {
        return success(ductCertificateService.createductCertificate(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新证书")
    @PreAuthorize("@ss.hasPermission('pro:duct-certificate:update')")
    public CommonResult<Boolean> updateductCertificate(@Valid @RequestBody ProductCertificateUpdateReqVO updateReqVO) {
        ductCertificateService.updateductCertificate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除证书")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-certificate:delete')")
    public CommonResult<Boolean> deleteductCertificate(@RequestParam("id") Long id) {
        ductCertificateService.deleteductCertificate(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得证书")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-certificate:query')")
    public CommonResult<ProductCertificateRespVO> getductCertificate(@RequestParam("id") Long id) {
        ProductCertificateDO ductCertificate = ductCertificateService.getductCertificate(id);
        return success(ProductCertificateConvert.INSTANCE.convert(ductCertificate));
    }

    @GetMapping("/list")
    @ApiOperation("获得证书列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-certificate:query')")
    public CommonResult<List<ProductCertificateRespVO>> getductCertificateList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductCertificateDO> list = ductCertificateService.getductCertificateList(ids);
        return success(ProductCertificateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得证书分页")
    @PreAuthorize("@ss.hasPermission('pro:duct-certificate:query')")
    public CommonResult<PageResult<ProductCertificateRespVO>> getductCertificatePage(@Valid ProductCertificatePageReqVO pageVO) {
        PageResult<ProductCertificateDO> pageResult = ductCertificateService.getductCertificatePage(pageVO);
        return success(ProductCertificateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出证书 Excel")
    @PreAuthorize("@ss.hasPermission('pro:duct-certificate:export')")
    @OperateLog(type = EXPORT)
    public void exportductCertificateExcel(@Valid ProductCertificateExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductCertificateDO> list = ductCertificateService.getductCertificateList(exportReqVO);
        // 导出 Excel
        List<ProductCertificateExcelVO> datas = ProductCertificateConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "证书.xls", "数据", ProductCertificateExcelVO.class, datas);
    }

}
