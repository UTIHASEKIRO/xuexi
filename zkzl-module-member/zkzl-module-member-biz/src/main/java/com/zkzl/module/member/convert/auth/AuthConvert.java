package com.zkzl.module.member.convert.auth;

import com.zkzl.module.member.controller.app.auth.vo.*;
import com.zkzl.module.member.controller.app.social.vo.AppSocialUserUnbindReqVO;
import com.zkzl.module.system.api.oauth2.dto.OAuth2AccessTokenRespDTO;
import com.zkzl.module.system.api.sms.dto.code.SmsCodeSendReqDTO;
import com.zkzl.module.system.api.sms.dto.code.SmsCodeUseReqDTO;
import com.zkzl.module.system.api.social.dto.SocialUserBindReqDTO;
import com.zkzl.module.system.api.social.dto.SocialUserUnbindReqDTO;
import com.zkzl.module.system.enums.sms.SmsSceneEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthConvert {

    AuthConvert INSTANCE = Mappers.getMapper(AuthConvert.class);

    SocialUserBindReqDTO convert(Long userId, Integer userType, AppAuthSocialLoginReqVO reqVO);
    SocialUserUnbindReqDTO convert(Long userId, Integer userType, AppSocialUserUnbindReqVO reqVO);

    SmsCodeSendReqDTO convert(AppAuthSmsSendReqVO reqVO);
    SmsCodeUseReqDTO convert(AppAuthResetPasswordReqVO reqVO, SmsSceneEnum scene, String usedIp);
    SmsCodeUseReqDTO convert(AppAuthSmsLoginReqVO reqVO, Integer scene, String usedIp);

    AppAuthLoginRespVO convert(OAuth2AccessTokenRespDTO bean);

}
