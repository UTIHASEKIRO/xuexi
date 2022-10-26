package com.zkzl.module.system.controller.admin.logger;

import com.zkzl.module.system.dal.dataobject.logger.LoginLogDO;
import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.excel.core.util.ExcelUtils;
import com.zkzl.framework.operatelog.core.annotations.OperateLog;
import com.zkzl.module.system.controller.admin.logger.vo.loginlog.LoginLogExcelVO;
import com.zkzl.module.system.controller.admin.logger.vo.loginlog.LoginLogExportReqVO;
import com.zkzl.module.system.controller.admin.logger.vo.loginlog.LoginLogPageReqVO;
import com.zkzl.module.system.controller.admin.logger.vo.loginlog.LoginLogRespVO;
import com.zkzl.module.system.convert.logger.LoginLogConvert;
import com.zkzl.module.system.service.logger.LoginLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.zkzl.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Api(tags = "管理后台 - 登录日志")
@RestController
@RequestMapping("/system/login-log")
@Validated
public class LoginLogController {

    @Resource
    private LoginLogService loginLogService;

    @GetMapping("/page")
    @ApiOperation("获得登录日志分页列表")
    @PreAuthorize("@ss.hasPermission('system:login-log:query')")
    public CommonResult<PageResult<LoginLogRespVO>> getLoginLogPage(@Valid LoginLogPageReqVO reqVO) {
        PageResult<LoginLogDO> page = loginLogService.getLoginLogPage(reqVO);
        return CommonResult.success(LoginLogConvert.INSTANCE.convertPage(page));
    }

    @GetMapping("/export")
    @ApiOperation("导出登录日志 Excel")
    @PreAuthorize("@ss.hasPermission('system:login-log:export')")
    @OperateLog(type = EXPORT)
    public void exportLoginLog(HttpServletResponse response, @Valid LoginLogExportReqVO reqVO) throws IOException {
        List<LoginLogDO> list = loginLogService.getLoginLogList(reqVO);
        // 拼接数据
        List<LoginLogExcelVO> data = LoginLogConvert.INSTANCE.convertList(list);
        // 输出
        ExcelUtils.write(response, "登录日志.xls", "数据列表", LoginLogExcelVO.class, data);
    }

}
