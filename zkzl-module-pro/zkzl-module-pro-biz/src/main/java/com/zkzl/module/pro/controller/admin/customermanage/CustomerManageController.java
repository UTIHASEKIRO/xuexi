package com.zkzl.module.pro.controller.admin.customermanage;

import com.zkzl.framework.operatelog.core.annotations.OperateLog;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.common.pojo.CommonResult;
import static com.zkzl.framework.common.pojo.CommonResult.success;
import static com.zkzl.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

import com.zkzl.framework.excel.core.util.ExcelUtils;

import com.zkzl.module.pro.controller.admin.customermanage.vo.*;
import com.zkzl.module.pro.dal.dataobject.customermanage.CustomerManageDO;
import com.zkzl.module.pro.convert.customermanage.CustomerManageConvert;
import com.zkzl.module.pro.service.customermanage.CustomerManageService;

@Api(tags = "管理后台 - 客户管理跟进")
@RestController
@RequestMapping("/pro/customer-manage")
@Validated
public class CustomerManageController {

    @Resource
    private CustomerManageService customerManageService;

//    @PostMapping("/create")
//    @ApiOperation("创建客户管理跟进")
//    @PreAuthorize("@ss.hasPermission('pro:customer-manage:create')")
//    public CommonResult<Long> createCustomerManage(@Valid @RequestBody CustomerManageCreateReqVO createReqVO) {
//        return success(customerManageService.createCustomerManage(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @ApiOperation("更新客户管理跟进")
//    @PreAuthorize("@ss.hasPermission('pro:customer-manage:update')")
//    public CommonResult<Boolean> updateCustomerManage(@Valid @RequestBody CustomerManageUpdateReqVO updateReqVO) {
//        customerManageService.updateCustomerManage(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @ApiOperation("删除客户管理跟进")
//    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
//    @PreAuthorize("@ss.hasPermission('pro:customer-manage:delete')")
//    public CommonResult<Boolean> deleteCustomerManage(@RequestParam("id") Long id) {
//        customerManageService.deleteCustomerManage(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @ApiOperation("获得客户管理跟进")
//    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
//    @PreAuthorize("@ss.hasPermission('pro:customer-manage:query')")
//    public CommonResult<CustomerManageRespVO> getCustomerManage(@RequestParam("id") Long id) {
//        CustomerManageDO customerManage = customerManageService.getCustomerManage(id);
//        return success(CustomerManageConvert.INSTANCE.convert(customerManage));
//    }
//
//    @GetMapping("/list")
//    @ApiOperation("获得客户管理跟进列表")
//    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
//    @PreAuthorize("@ss.hasPermission('pro:customer-manage:query')")
//    public CommonResult<List<CustomerManageRespVO>> getCustomerManageList(@RequestParam("ids") Collection<Long> ids) {
//        List<CustomerManageDO> list = customerManageService.getCustomerManageList(ids);
//        return success(CustomerManageConvert.INSTANCE.convertList(list));
//    }
//
//    @GetMapping("/page")
//    @ApiOperation("获得客户管理跟进分页")
//    @PreAuthorize("@ss.hasPermission('pro:customer-manage:query')")
//    public CommonResult<PageResult<CustomerManageRespVO>> getCustomerManagePage(@Valid CustomerManagePageReqVO pageVO) {
//        PageResult<CustomerManageDO> pageResult = customerManageService.getCustomerManagePage(pageVO);
//        return success(CustomerManageConvert.INSTANCE.convertPage(pageResult));
//    }
//
//    @GetMapping("/export-excel")
//    @ApiOperation("导出客户管理跟进 Excel")
//    @PreAuthorize("@ss.hasPermission('pro:customer-manage:export')")
//    @OperateLog(type = EXPORT)
//    public void exportCustomerManageExcel(@Valid CustomerManageExportReqVO exportReqVO,
//              HttpServletResponse response) throws IOException {
//        List<CustomerManageDO> list = customerManageService.getCustomerManageList(exportReqVO);
//        // 导出 Excel
//        List<CustomerManageExcelVO> datas = CustomerManageConvert.INSTANCE.convertList02(list);
//        ExcelUtils.write(response, "客户管理跟进.xls", "数据", CustomerManageExcelVO.class, datas);
//    }

    @GetMapping("/pageCustFollow")
    @ApiOperation("获得客户管理跟进分页")
    //@PreAuthorize("@ss.hasPermission('pro:customer-manage:query')")
    public CommonResult<PageResult<CustomerManageRespVO>> pageCustFollow(@Valid CustomerManagePageReqVO pageVO) {
        PageResult<CustomerManageDO> pageResult = customerManageService.pageCustFollow(pageVO);
        return success(CustomerManageConvert.INSTANCE.convertPage(pageResult));
    }
}
