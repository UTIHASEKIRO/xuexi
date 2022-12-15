package com.zkzl.module.pro.controller.app.user;

import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.module.pro.controller.admin.cususer.vo.CusUserCreateReqVO;
import com.zkzl.module.pro.service.cususer.CusUserService;
import com.zkzl.module.system.service.user.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "客户端 - 用户")
@RestController
@RequestMapping("/user")
@Validated
public class ApiCusUserController {

    @Resource
    private AdminUserService userService;

    @Resource
    private CusUserService cusUserService;

    @PostMapping("/create")
    @ApiOperation("用户注册")
    @Transactional(rollbackFor = Exception.class)
    @PermitAll
    public CommonResult<Long> createUser(@Valid @RequestBody CusUserCreateReqVO reqVO) {
        Long userId = userService.createUser(reqVO);
        reqVO.setUserId(userId);
        return success(cusUserService.createCusUser(reqVO));
    }
}
