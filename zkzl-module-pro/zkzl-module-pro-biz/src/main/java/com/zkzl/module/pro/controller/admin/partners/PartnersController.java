package com.zkzl.module.pro.controller.admin.partners;

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

import com.zkzl.module.pro.controller.admin.partners.vo.*;
import com.zkzl.module.pro.dal.dataobject.partners.PartnersDO;
import com.zkzl.module.pro.convert.partners.PartnersConvert;
import com.zkzl.module.pro.service.partners.PartnersService;

@Api(tags = "管理后台 - 合作伙伴")
@RestController
@RequestMapping("/pro/partners")
@Validated
public class PartnersController {

    @Resource
    private PartnersService partnersService;

    @PostMapping("/create")
    @ApiOperation("创建合作伙伴")
    @PreAuthorize("@ss.hasPermission('pro:partners:create')")
    public CommonResult<Long> createPartners(@Valid @RequestBody PartnersCreateReqVO createReqVO) {
        return success(partnersService.createPartners(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新合作伙伴")
    @PreAuthorize("@ss.hasPermission('pro:partners:update')")
    public CommonResult<Boolean> updatePartners(@Valid @RequestBody PartnersUpdateReqVO updateReqVO) {
        partnersService.updatePartners(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除合作伙伴")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:partners:delete')")
    public CommonResult<Boolean> deletePartners(@RequestParam("id") Long id) {
        partnersService.deletePartners(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得合作伙伴")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:partners:query')")
    public CommonResult<PartnersRespVO> getPartners(@RequestParam("id") Long id) {
        PartnersDO partners = partnersService.getPartners(id);
        return success(PartnersConvert.INSTANCE.convert(partners));
    }

    @GetMapping("/list")
    @ApiOperation("获得合作伙伴列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:partners:query')")
    public CommonResult<List<PartnersRespVO>> getPartnersList(@RequestParam("ids") Collection<Long> ids) {
        List<PartnersDO> list = partnersService.getPartnersList(ids);
        return success(PartnersConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得合作伙伴分页")
    @PreAuthorize("@ss.hasPermission('pro:partners:query')")
    public CommonResult<PageResult<PartnersRespVO>> getPartnersPage(@Valid PartnersPageReqVO pageVO) {
        PageResult<PartnersDO> pageResult = partnersService.getPartnersPage(pageVO);
        return success(PartnersConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出合作伙伴 Excel")
    @PreAuthorize("@ss.hasPermission('pro:partners:export')")
    @OperateLog(type = EXPORT)
    public void exportPartnersExcel(@Valid PartnersExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PartnersDO> list = partnersService.getPartnersList(exportReqVO);
        // 导出 Excel
        List<PartnersExcelVO> datas = PartnersConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "合作伙伴.xls", "数据", PartnersExcelVO.class, datas);
    }

}
