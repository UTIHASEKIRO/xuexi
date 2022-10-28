package com.zkzl.module.pro.service.customermanage;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.customermanage.vo.*;
import com.zkzl.module.pro.dal.dataobject.customermanage.CustomerManageDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 客户管理跟进 Service 接口
 *
 * @author 芋道源码
 */
public interface CustomerManageService {

    /**
     * 创建客户管理跟进
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCustomerManage(@Valid CustomerManageCreateReqVO createReqVO);

    /**
     * 更新客户管理跟进
     *
     * @param updateReqVO 更新信息
     */
    void updateCustomerManage(@Valid CustomerManageUpdateReqVO updateReqVO);

    /**
     * 删除客户管理跟进
     *
     * @param id 编号
     */
    void deleteCustomerManage(Long id);

    /**
     * 获得客户管理跟进
     *
     * @param id 编号
     * @return 客户管理跟进
     */
    CustomerManageDO getCustomerManage(Long id);

    /**
     * 获得客户管理跟进列表
     *
     * @param ids 编号
     * @return 客户管理跟进列表
     */
    List<CustomerManageDO> getCustomerManageList(Collection<Long> ids);

    /**
     * 获得客户管理跟进分页
     *
     * @param pageReqVO 分页查询
     * @return 客户管理跟进分页
     */
    PageResult<CustomerManageDO> getCustomerManagePage(CustomerManagePageReqVO pageReqVO);

    /**
     * 获得客户管理跟进列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 客户管理跟进列表
     */
    List<CustomerManageDO> getCustomerManageList(CustomerManageExportReqVO exportReqVO);

}
