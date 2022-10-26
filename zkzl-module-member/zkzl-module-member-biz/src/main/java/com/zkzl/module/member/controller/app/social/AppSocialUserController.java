package com.zkzl.module.member.controller.app.social;

import com.zkzl.framework.common.enums.UserTypeEnum;
import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.module.member.controller.app.social.vo.AppSocialUserBindReqVO;
import com.zkzl.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import com.zkzl.module.member.convert.social.SocialUserConvert;
import com.zkzl.module.system.api.social.SocialUserApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.zkzl.framework.security.core.util.SecurityFrameworkUtils.getLoginUserId;

@Api(tags = "用户 App - 社交用户")
@RestController
@RequestMapping("/system/social-user")
@Validated
public class AppSocialUserController {

    @Resource
    private SocialUserApi socialUserApi;

    @PostMapping("/bind")
    @ApiOperation("社交绑定，使用 code 授权码")
    public CommonResult<Boolean> socialBind(@RequestBody @Valid AppSocialUserBindReqVO reqVO) {
        socialUserApi.bindSocialUser(SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.MEMBER.getValue(), reqVO));
        return CommonResult.success(true);
    }

    @DeleteMapping("/unbind")
    @ApiOperation("取消社交绑定")
    public CommonResult<Boolean> socialUnbind(@RequestBody AppSocialUserUnbindReqVO reqVO) {
        socialUserApi.unbindSocialUser(SocialUserConvert.INSTANCE.convert(getLoginUserId(), UserTypeEnum.MEMBER.getValue(), reqVO));
        return CommonResult.success(true);
    }

}
