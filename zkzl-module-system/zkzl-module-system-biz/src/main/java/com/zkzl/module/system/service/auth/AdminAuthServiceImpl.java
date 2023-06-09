package com.zkzl.module.system.service.auth;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.annotations.VisibleForTesting;
import com.zkzl.framework.common.enums.CommonStatusEnum;
import com.zkzl.framework.common.enums.UserTypeEnum;
import com.zkzl.framework.common.util.monitor.TracerUtils;
import com.zkzl.framework.common.util.servlet.ServletUtils;
import com.zkzl.framework.common.util.validation.ValidationUtils;
import com.zkzl.module.pro.api.cususer.CusUserApi;
import com.zkzl.module.pro.api.cususer.vo.CusUserDTO;
import com.zkzl.module.system.api.logger.dto.LoginLogCreateReqDTO;
import com.zkzl.module.system.api.sms.SmsCodeApi;
import com.zkzl.module.system.api.social.dto.SocialUserBindReqDTO;
import com.zkzl.module.system.controller.admin.auth.vo.*;
import com.zkzl.module.system.convert.auth.AuthConvert;
import com.zkzl.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import com.zkzl.module.system.dal.dataobject.user.AdminUserDO;
import com.zkzl.module.system.enums.logger.LoginLogTypeEnum;
import com.zkzl.module.system.enums.logger.LoginResultEnum;
import com.zkzl.module.system.enums.oauth2.OAuth2ClientConstants;
import com.zkzl.module.system.enums.sms.SmsSceneEnum;
import com.zkzl.module.system.service.common.CaptchaService;
import com.zkzl.module.system.service.logger.LoginLogService;
import com.zkzl.module.system.service.member.MemberService;
import com.zkzl.module.system.service.oauth2.OAuth2TokenService;
import com.zkzl.module.system.service.social.SocialUserService;
import com.zkzl.module.system.service.user.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Validator;
import java.util.Objects;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.framework.common.util.servlet.ServletUtils.getClientIP;
import static com.zkzl.module.system.enums.ErrorCodeConstants.*;

/**
 * Auth Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Slf4j
public class AdminAuthServiceImpl implements AdminAuthService {

    @Resource
    private AdminUserService userService;
    @Resource
    private CaptchaService captchaService;
    @Resource
    private LoginLogService loginLogService;
    @Resource
    private OAuth2TokenService oauth2TokenService;
    @Resource
    private SocialUserService socialUserService;
    @Resource
    private MemberService memberService;

    @Resource
    private Validator validator;
    @Resource
    private CusUserApi cusUserApi;
    @Resource
    private SmsCodeApi smsCodeApi;

    @Override
    public AdminUserDO authenticate(String username, String password) {
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        // 校验账号是否存在
        AdminUserDO user = userService.getUserByUsername(username);
        if (user == null) {
            createLoginLog(null, username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.BAD_CREDENTIALS);
            throw exception(AUTH_LOGIN_BAD_CREDENTIALS);
        }
        // 校验是否禁用
        if (ObjectUtil.notEqual(user.getStatus(), CommonStatusEnum.ENABLE.getStatus())) {
            createLoginLog(user.getId(), username, logTypeEnum, LoginResultEnum.USER_DISABLED);
            throw exception(AUTH_LOGIN_USER_DISABLED);
        }
        return user;
    }

    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {
        // 判断验证码是否正确
        verifyCaptcha(reqVO);

        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());

        CusUserDTO cusUserDTO = cusUserApi.getUser(user.getId());
        // 管理后台与客户端登录分离
        if ((reqVO.getLoginType() == null || "0".equals(reqVO.getLoginType())) && cusUserDTO != null) {
            throw exception(AUTH_PURVIEW_NOT_EXISTS);
        }
        if ("1".equals(reqVO.getLoginType()) && cusUserDTO == null) {
            throw exception(AUTH_PURVIEW_NOT_EXISTS);
        }
        // 如果 socialType 非空，说明需要绑定社交用户
        if (reqVO.getSocialType() != null) {
            socialUserService.bindSocialUser(new SocialUserBindReqDTO(user.getId(), getUserType(reqVO.getLoginType()).getValue(),
                    reqVO.getSocialType(), reqVO.getSocialCode(), reqVO.getSocialState()));
        }

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), reqVO.getUsername(), LoginLogTypeEnum.LOGIN_USERNAME,reqVO.getLoginType());
    }

    @Override
    public void sendSmsCode(AuthSmsSendReqVO reqVO) {
        // 登录场景，验证是否存在
        if (userService.getUserByMobile(reqVO.getMobile()) == null) {
            throw exception(AUTH_MOBILE_NOT_EXISTS);
        }
        // 发送验证码
        smsCodeApi.sendSmsCode(AuthConvert.INSTANCE.convert(reqVO).setCreateIp(getClientIP()));
    }

    @Override
    public AuthLoginRespVO smsLogin(AuthSmsLoginReqVO reqVO) {
        // 校验验证码
        smsCodeApi.useSmsCode(AuthConvert.INSTANCE.convert(reqVO, SmsSceneEnum.ADMIN_MEMBER_LOGIN.getScene(), getClientIP()));

        // 获得用户信息
        AdminUserDO user = userService.getUserByMobile(reqVO.getMobile());
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), reqVO.getMobile(), LoginLogTypeEnum.LOGIN_MOBILE,null);
    }

    @VisibleForTesting
    void verifyCaptcha(AuthLoginReqVO reqVO) {
        // 如果验证码关闭，则不进行校验
        if (!captchaService.isCaptchaEnable()) {
            return;
        }
        // 校验验证码
        ValidationUtils.validate(validator, reqVO, AuthLoginReqVO.CodeEnableGroup.class);
        // 验证码不存在
        final LoginLogTypeEnum logTypeEnum = LoginLogTypeEnum.LOGIN_USERNAME;
        String code = captchaService.getCaptchaCode(reqVO.getUuid());
        if (code == null) {
            // 创建登录失败日志（验证码不存在）
            createLoginLog(null, reqVO.getUsername(), logTypeEnum, LoginResultEnum.CAPTCHA_NOT_FOUND);
            throw exception(AUTH_LOGIN_CAPTCHA_NOT_FOUND);
        }
        // 验证码不正确
        if (!code.equals(reqVO.getCode())) {
            // 创建登录失败日志（验证码不正确)
            createLoginLog(null, reqVO.getUsername(), logTypeEnum, LoginResultEnum.CAPTCHA_CODE_ERROR);
            throw exception(AUTH_LOGIN_CAPTCHA_CODE_ERROR);
        }
        // 正确，所以要删除下验证码
        captchaService.deleteCaptchaCode(reqVO.getUuid());
    }

    private void createLoginLog(Long userId, String username,
                                LoginLogTypeEnum logTypeEnum, LoginResultEnum loginResult) {
        // 插入登录日志
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logTypeEnum.getType());
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(getUserType(null).getValue());
        reqDTO.setUsername(username);
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(loginResult.getResult());
        loginLogService.createLoginLog(reqDTO);
        // 更新最后登录时间
        if (userId != null && Objects.equals(LoginResultEnum.SUCCESS.getResult(), loginResult.getResult())) {
            userService.updateUserLogin(userId, ServletUtils.getClientIP());
        }
    }

    @Override
    public AuthLoginRespVO socialLogin(AuthSocialLoginReqVO reqVO) {
        // 使用 code 授权码，进行登录。然后，获得到绑定的用户编号
        Long userId = socialUserService.getBindUserId(UserTypeEnum.ADMIN.getValue(), reqVO.getType(),
                reqVO.getCode(), reqVO.getState());
        if (userId == null) {
            throw exception(AUTH_THIRD_LOGIN_NOT_BIND);
        }

        // 获得用户
        AdminUserDO user = userService.getUser(userId);
        if (user == null) {
            throw exception(USER_NOT_EXISTS);
        }

        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId(), user.getUsername(), LoginLogTypeEnum.LOGIN_SOCIAL,null);
    }

    @Override
    public AuthLoginRespVO refreshToken(String refreshToken) {
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.refreshAccessToken(refreshToken, OAuth2ClientConstants.CLIENT_ID_DEFAULT);
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId, String username, LoginLogTypeEnum logType, String userType) {
        // 插入登陆日志
        createLoginLog(userId, username, logType, LoginResultEnum.SUCCESS);
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId, getUserType(userType).getValue(),
                OAuth2ClientConstants.CLIENT_ID_DEFAULT, null);
        // 构建返回结果
        return AuthConvert.INSTANCE.convert(accessTokenDO);
    }

    @Override
    public void logout(String token, Integer logType) {
        // 删除访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.removeAccessToken(token);
        if (accessTokenDO == null) {
            return;
        }
        // 删除成功，则记录登出日志
        createLogoutLog(accessTokenDO.getUserId(), accessTokenDO.getUserType(), logType);
    }

    private void createLogoutLog(Long userId, Integer userType, Integer logType) {
        LoginLogCreateReqDTO reqDTO = new LoginLogCreateReqDTO();
        reqDTO.setLogType(logType);
        reqDTO.setTraceId(TracerUtils.getTraceId());
        reqDTO.setUserId(userId);
        reqDTO.setUserType(userType);
        if (ObjectUtil.equal(getUserType(null).getValue(), userType)) {
            reqDTO.setUsername(getUsername(userId));
        } else {
            reqDTO.setUsername(memberService.getMemberUserMobile(userId));
        }
        reqDTO.setUserAgent(ServletUtils.getUserAgent());
        reqDTO.setUserIp(ServletUtils.getClientIP());
        reqDTO.setResult(LoginResultEnum.SUCCESS.getResult());
        loginLogService.createLoginLog(reqDTO);
    }

    private String getUsername(Long userId) {
        if (userId == null) {
            return null;
        }
        AdminUserDO user = userService.getUser(userId);
        return user != null ? user.getUsername() : null;
    }

    private UserTypeEnum getUserType(String userType) {
        if("1".equals(userType)){
            return UserTypeEnum.MEMBER;
        }else {
            return UserTypeEnum.ADMIN;
        }
    }

}
