package com.zkzl.module.pro.api.cususer;

import com.zkzl.module.pro.api.cususer.vo.CusUserDTO;

public interface CusUserApi {


    /**
     * 获得客户信息
     *
     * @param userId 用户ID
     * @return 客户信息
     */
    CusUserDTO getUser(Long userId);

}
