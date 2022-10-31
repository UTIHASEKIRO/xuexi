package com.zkzl.module.pro.service.cususer;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import com.zkzl.module.system.api.user.AdminUserApi;
import com.zkzl.module.system.service.user.AdminUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.cususer.vo.*;
import com.zkzl.module.pro.dal.dataobject.cususer.CusUserDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.cususer.CusUserConvert;
import com.zkzl.module.pro.dal.mysql.cususer.CusUserMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.CUS_USER_NOT_EXISTS;

/**
 * 客户公司信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CusUserServiceImpl implements CusUserService {

    @Resource
    private CusUserMapper cusUserMapper;

    @Resource
    private AdminUserService adminUserService;

    @Override
    public Long createCusUser(CusUserCreateReqVO createReqVO) {
        // 插入
        CusUserDO cusUser = CusUserConvert.INSTANCE.convert(createReqVO);
        cusUserMapper.insert(cusUser);
        // 返回
        return cusUser.getUserId();
    }

    @Override
    public void updateCusUser(CusUserUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCusUserExists(updateReqVO.getUserId());
        // 更新
        CusUserDO updateObj = CusUserConvert.INSTANCE.convert(updateReqVO);
        cusUserMapper.updateById(updateObj);
    }

    @Override
    public void deleteCusUser(Long id) {
        // 校验存在
        this.validateCusUserExists(id);
        // 删除
        cusUserMapper.deleteById(id);
    }

    private void validateCusUserExists(Long id) {
        if (cusUserMapper.selectById(id) == null) {
            throw exception(CUS_USER_NOT_EXISTS);
        }
    }

    @Override
    public CusUserDO getCusUser(Long id) {
        return cusUserMapper.selectById(id);
    }

    @Override
    public List<CusUserDO> getCusUserList(Collection<Long> ids) {
        return cusUserMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CusUserDO> getCusUserPage(CusUserPageReqVO pageReqVO) {
        return cusUserMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CusUserDO> getCusUserList(CusUserExportReqVO exportReqVO) {
        return cusUserMapper.selectList(exportReqVO);
    }

    @Override
    public PageResult<CusUserDO> pageCustomer(CusUserPageReqVO pageVO) {
        IPage<CusUserDO> mpPage = MyBatisUtils.buildPage(pageVO);
        cusUserMapper.cusFromSysUser(mpPage,pageVO);

        return new PageResult<>(mpPage.getRecords(), mpPage.getTotal());
    }

}
