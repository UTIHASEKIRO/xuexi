package com.zkzl.module.pro.api.cususer;

import com.zkzl.module.pro.api.cususer.vo.CusUserDTO;
import com.zkzl.module.pro.convert.cususer.CusUserConvert;
import com.zkzl.module.pro.dal.dataobject.cususer.CusUserDO;
import com.zkzl.module.pro.service.cususer.CusUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 客户 API 实现类
 *
 * @author 芋道源码
 */
@Service
public class CusUserApiImpl implements CusUserApi {

    @Resource
    private CusUserService cusUserService;

    @Override
    public CusUserDTO getUser(Long userId) {
        CusUserDO cusUser = cusUserService.getCusUser(userId);
        return CusUserConvert.INSTANCE.convert02(cusUser);
    }
}
