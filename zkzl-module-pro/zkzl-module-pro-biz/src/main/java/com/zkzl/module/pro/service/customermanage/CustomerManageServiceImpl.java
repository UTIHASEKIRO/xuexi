package com.zkzl.module.pro.service.customermanage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import com.zkzl.module.pro.dal.dataobject.cususer.CusUserDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.customermanage.vo.*;
import com.zkzl.module.pro.dal.dataobject.customermanage.CustomerManageDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.customermanage.CustomerManageConvert;
import com.zkzl.module.pro.dal.mysql.customermanage.CustomerManageMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.CUSTOMER_MANAGE_NOT_EXISTS;


/**
 * 客户管理跟进 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CustomerManageServiceImpl implements CustomerManageService {

    @Resource
    private CustomerManageMapper customerManageMapper;

    @Override
    public Long createCustomerManage(CustomerManageCreateReqVO createReqVO) {
        // 插入
        CustomerManageDO customerManage = CustomerManageConvert.INSTANCE.convert(createReqVO);
        customerManageMapper.insert(customerManage);
        // 返回
        return customerManage.getUserId();
    }

    @Override
    public void updateCustomerManage(CustomerManageUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCustomerManageExists(updateReqVO.getUserId());
        // 更新
        CustomerManageDO updateObj = CustomerManageConvert.INSTANCE.convert(updateReqVO);
        customerManageMapper.updateById(updateObj);
    }

    @Override
    public void deleteCustomerManage(Long id) {
        // 校验存在
        this.validateCustomerManageExists(id);
        // 删除
        customerManageMapper.deleteById(id);
    }

    private void validateCustomerManageExists(Long id) {
        if (customerManageMapper.selectById(id) == null) {
            throw exception(CUSTOMER_MANAGE_NOT_EXISTS);
        }
    }

    @Override
    public CustomerManageDO getCustomerManage(Long id) {
        return customerManageMapper.selectById(id);
    }

    @Override
    public List<CustomerManageDO> getCustomerManageList(Collection<Long> ids) {
        return customerManageMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CustomerManageDO> getCustomerManagePage(CustomerManagePageReqVO pageReqVO) {
        return customerManageMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CustomerManageDO> getCustomerManageList(CustomerManageExportReqVO exportReqVO) {
        return customerManageMapper.selectList(exportReqVO);
    }

    @Override
    public PageResult<CustomerManageDO> pageCustFollow(CustomerManagePageReqVO pageVO) {
        IPage<CustomerManageDO> page = MyBatisUtils.buildPage(pageVO);
        customerManageMapper.pageCustFollow(page,pageVO);
        return new PageResult<>(page.getRecords(),page.getTotal());
    }

}
