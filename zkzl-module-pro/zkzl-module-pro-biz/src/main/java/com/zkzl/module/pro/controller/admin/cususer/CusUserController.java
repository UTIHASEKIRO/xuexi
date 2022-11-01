package com.zkzl.module.pro.controller.admin.cususer;

import com.zkzl.module.system.dal.dataobject.user.AdminUserDO;
import com.zkzl.module.system.service.user.AdminUserService;
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

import com.zkzl.module.pro.controller.admin.cususer.vo.*;
import com.zkzl.module.pro.dal.dataobject.cususer.CusUserDO;
import com.zkzl.module.pro.convert.cususer.CusUserConvert;
import com.zkzl.module.pro.service.cususer.CusUserService;

@Api(tags = "管理后台 - 客户公司信息")
@RestController
@RequestMapping("/pro/cus-user")
@Validated
public class CusUserController {

    @Resource
    private CusUserService cusUserService;

    @Resource
    private AdminUserService adminUserService;

//    @PostMapping("/create")
//    @ApiOperation("创建客户公司信息")
//    @PreAuthorize("@ss.hasPermission('pro:cus-user:create')")
//    public CommonResult<Long> createCusUser(@Valid @RequestBody CusUserCreateReqVO createReqVO) {
//        return success(cusUserService.createCusUser(createReqVO));
//    }
//
//    @PutMapping("/update")
//    @ApiOperation("更新客户公司信息")
//    @PreAuthorize("@ss.hasPermission('pro:cus-user:update')")
//    public CommonResult<Boolean> updateCusUser(@Valid @RequestBody CusUserUpdateReqVO updateReqVO) {
//        cusUserService.updateCusUser(updateReqVO);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @ApiOperation("删除客户公司信息")
//    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
//    @PreAuthorize("@ss.hasPermission('pro:cus-user:delete')")
//    public CommonResult<Boolean> deleteCusUser(@RequestParam("id") Long id) {
//        cusUserService.deleteCusUser(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @ApiOperation("获得客户公司信息")
//    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
//    @PreAuthorize("@ss.hasPermission('pro:cus-user:query')")
//    public CommonResult<CusUserRespVO> getCusUser(@RequestParam("id") Long id) {
//        CusUserDO cusUser = cusUserService.getCusUser(id);
//        return success(CusUserConvert.INSTANCE.convert(cusUser));
//    }
//
//    @GetMapping("/list")
//    @ApiOperation("获得客户公司信息列表")
//    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
//    @PreAuthorize("@ss.hasPermission('pro:cus-user:query')")
//    public CommonResult<List<CusUserRespVO>> getCusUserList(@RequestParam("ids") Collection<Long> ids) {
//        List<CusUserDO> list = cusUserService.getCusUserList(ids);
//        return success(CusUserConvert.INSTANCE.convertList(list));
//    }
//
/*    @GetMapping("/page")
    @ApiOperation("获得客户公司信息分页")
    @PreAuthorize("@ss.hasPermission('pro:cus-user:query')")
    public CommonResult<PageResult<CusUserRespVO>> getCusUserPage(@Valid CusUserPageReqVO pageVO) {
        PageResult<CusUserDO> pageResult = cusUserService.getCusUserPage(pageVO);
        List<CusUserDO> list = pageResult.getList();
        AdminUserDO user = new AdminUserDO();
        for (CusUserDO cusUserDO : list) {
            AdminUserDO user = adminUserService.getUser(cusUserDO.getUserId());
        }
        return success(CusUserConvert.INSTANCE.convertPage(pageResult));
    }*/
//
//    @GetMapping("/export-excel")
//    @ApiOperation("导出客户公司信息 Excel")
//    @PreAuthorize("@ss.hasPermission('pro:cus-user:export')")
//    @OperateLog(type = EXPORT)
//    public void exportCusUserExcel(@Valid CusUserExportReqVO exportReqVO,
//              HttpServletResponse response) throws IOException {
//        List<CusUserDO> list = cusUserService.getCusUserList(exportReqVO);
//        // 导出 Excel
//        List<CusUserExcelVO> datas = CusUserConvert.INSTANCE.convertList02(list);
//        ExcelUtils.write(response, "客户公司信息.xls", "数据", CusUserExcelVO.class, datas);
//    }

    /*
    * 只有管理员（老板能看）
    * */
    @GetMapping("/pageCustomer")
    @ApiOperation("获得客户公司信息分页")
    @PreAuthorize("@ss.hasPermission('pro:cus-user:query')")
    public CommonResult<PageResult<CusUserRespVO>> pageCustomer(@Valid CusUserPageReqVO pageVO) {
        PageResult<CusUserDO> pageResult = cusUserService.pageCustomer(pageVO);
        return success(CusUserConvert.INSTANCE.convertPage(pageResult));
    }
}
