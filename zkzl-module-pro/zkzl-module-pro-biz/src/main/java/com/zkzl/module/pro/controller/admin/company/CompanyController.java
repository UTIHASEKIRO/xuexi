package com.zkzl.module.pro.controller.admin.company;

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

import com.zkzl.module.pro.controller.admin.company.vo.*;
import com.zkzl.module.pro.dal.dataobject.company.CompanyDO;
import com.zkzl.module.pro.convert.company.CompanyConvert;
import com.zkzl.module.pro.service.company.CompanyService;

/**
 * 管理后台 - 公司信息维护
 */
@Api(tags = "管理后台 - 公司信息维护")
@RestController
@RequestMapping("/pro/company")
@Validated
public class CompanyController {

    @Resource
    private CompanyService companyService;

    /**
     * 创建公司信息维护
     * @param createReqVO
     * @return
     */
    @PostMapping("/create")
    @ApiOperation("创建公司信息维护")
    @PreAuthorize("@ss.hasPermission('pro:company:create')")
    public CommonResult<Long> createCompany(@Valid @RequestBody CompanyCreateReqVO createReqVO) {
        return success(companyService.createCompany(createReqVO));
    }

    /**
     * 更新公司信息维护
     * @param updateReqVO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("更新公司信息维护")
    @PreAuthorize("@ss.hasPermission('pro:company:update')")
    public CommonResult<Boolean> updateCompany(@Valid @RequestBody CompanyUpdateReqVO updateReqVO) {
        companyService.updateCompany(updateReqVO);
        return success(true);
    }

    /**
     * 删除公司信息维护
     * @param id
     * @return
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除公司信息维护")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:company:delete')")
    public CommonResult<Boolean> deleteCompany(@RequestParam("id") Long id) {
        companyService.deleteCompany(id);
        return success(true);
    }

    /**
     * 获得公司信息维护
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("获得公司信息维护")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:company:query')")
    public CommonResult<CompanyRespVO> getCompany(@RequestParam("id") Long id) {
        CompanyDO company = companyService.getCompany(id);
        return success(CompanyConvert.INSTANCE.convert(company));
    }

    /**
     * 获得公司信息维护列表
     * @param ids
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("获得公司信息维护列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:company:query')")
    public CommonResult<List<CompanyRespVO>> getCompanyList(@RequestParam("ids") Collection<Long> ids) {
        List<CompanyDO> list = companyService.getCompanyList(ids);
        return success(CompanyConvert.INSTANCE.convertList(list));
    }

    /**
     * 获得公司信息维护分页
     * @param pageVO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("获得公司信息维护分页")
    @PreAuthorize("@ss.hasPermission('pro:company:query')")
    public CommonResult<PageResult<CompanyRespVO>> getCompanyPage(@Valid CompanyPageReqVO pageVO) {
        PageResult<CompanyDO> pageResult = companyService.getCompanyPage(pageVO);
        return success(CompanyConvert.INSTANCE.convertPage(pageResult));
    }

    /**
     * 导出公司信息维护 Excel
     * @param exportReqVO
     * @param response
     * @throws IOException
     */
    @GetMapping("/export-excel")
    @ApiOperation("导出公司信息维护 Excel")
    @PreAuthorize("@ss.hasPermission('pro:company:export')")
    @OperateLog(type = EXPORT)
    public void exportCompanyExcel(@Valid CompanyExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CompanyDO> list = companyService.getCompanyList(exportReqVO);
        // 导出 Excel
        List<CompanyExcelVO> datas = CompanyConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "公司信息维护.xls", "数据", CompanyExcelVO.class, datas);
    }

}
