package com.zkzl.module.member.convert.user;

import com.zkzl.module.member.api.user.dto.UserInfoDTO;
import com.zkzl.module.member.api.user.dto.UserRespDTO;
import com.zkzl.module.member.controller.app.user.vo.AppUserInfoRespVO;
import com.zkzl.module.member.dal.dataobject.user.MemberUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    AppUserInfoRespVO convert(MemberUserDO bean);

    UserRespDTO convert2(MemberUserDO bean);
    UserInfoDTO convertInfo(MemberUserDO bean);
}
