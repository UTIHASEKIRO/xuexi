package com.zkzl.module.pro.controller.admin.supplyinfo;

import com.zkzl.module.pro.controller.admin.producttype.vo.ProductTypeRespVO;
import com.zkzl.module.pro.convert.producttype.ProductTypeConvert;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;
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

import com.zkzl.module.pro.controller.admin.supplyinfo.vo.*;
import com.zkzl.module.pro.dal.dataobject.supplyinfo.SupplyInfoDO;
import com.zkzl.module.pro.convert.supplyinfo.SupplyInfoConvert;
import com.zkzl.module.pro.service.supplyinfo.SupplyInfoService;

@Api(tags = "管理后台 - 供货商信息")
@RestController
@RequestMapping("/pro/supply-info")
@Validated
public class SupplyInfoController {

    @Resource
    private SupplyInfoService supplyInfoService;

    @PostMapping("/create")
    @ApiOperation("创建供货商信息")
    @PreAuthorize("@ss.hasPermission('pro:supply-info:create')")
    public CommonResult<Long> createSupplyInfo(@Valid @RequestBody SupplyInfoCreateReqVO createReqVO) {
        return success(supplyInfoService.createSupplyInfo(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新供货商信息")
    @PreAuthorize("@ss.hasPermission('pro:supply-info:update')")
    public CommonResult<Boolean> updateSupplyInfo(@Valid @RequestBody SupplyInfoUpdateReqVO updateReqVO) {
        supplyInfoService.updateSupplyInfo(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除供货商信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:supply-info:delete')")
    public CommonResult<Boolean> deleteSupplyInfo(@RequestParam("id") Long id) {
        supplyInfoService.deleteSupplyInfo(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得供货商信息")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:supply-info:query')")
    public CommonResult<SupplyInfoRespVO> getSupplyInfo(@RequestParam("id") Long id) {
        SupplyInfoDO supplyInfo = supplyInfoService.getSupplyInfo(id);
        return success(SupplyInfoConvert.INSTANCE.convert(supplyInfo));
    }

    @GetMapping("/list")
    @ApiOperation("获得供货商信息列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:supply-info:query')")
    public CommonResult<List<SupplyInfoRespVO>> getSupplyInfoList(@RequestParam("ids") Collection<Long> ids) {
        List<SupplyInfoDO> list = supplyInfoService.getSupplyInfoList(ids);
        return success(SupplyInfoConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得供货商信息分页")
    @PreAuthorize("@ss.hasPermission('pro:supply-info:query')")
    public CommonResult<PageResult<SupplyInfoPageVO>> getSupplyInfoPage(@Valid SupplyInfoPageReqVO pageVO) {
        PageResult<SupplyInfoPageVO> pageResult = supplyInfoService.getSupplyInfoPage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/pageName")
    @ApiOperation("获得供货商名称")
    @PreAuthorize("@ss.hasPermission('pro:supply-info:query')")
    public CommonResult<PageResult<SupplyInfoNameVO>> getSupplyInfoName(@Valid SupplyInfoPageReqVO pageVO) {
        PageResult<SupplyInfoNameVO> pageResult = supplyInfoService.getSupplyInfoName(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出供货商信息 Excel")
    @PreAuthorize("@ss.hasPermission('pro:supply-info:export')")
    @OperateLog(type = EXPORT)
    public void exportSupplyInfoExcel(@Valid SupplyInfoExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SupplyInfoDO> list = supplyInfoService.getSupplyInfoList(exportReqVO);
        // 导出 Excel
        List<SupplyInfoExcelVO> datas = SupplyInfoConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "供货商信息.xls", "数据", SupplyInfoExcelVO.class, datas);
    }

    @GetMapping("/product-type")
    @ApiOperation("获得供货商信息-产品类别")
    public CommonResult<List<ProductTypeRespVO>> getProductType() {
        List<ProductTypeDO> result = supplyInfoService.getProductType();
        return success(ProductTypeConvert.INSTANCE.convertList(result));
    }

}
