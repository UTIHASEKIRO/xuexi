package com.zkzl.module.pro.service.cususer;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.cususer.vo.*;
import com.zkzl.module.pro.dal.dataobject.cususer.CusUserDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 客户公司信息 Service 接口
 *
 * @author 芋道源码
 */
public interface CusUserService {

    /**
     * 创建客户公司信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCusUser(@Valid CusUserCreateReqVO createReqVO);

    /**
     * 更新客户公司信息
     *
     * @param updateReqVO 更新信息
     */
    void updateCusUser(@Valid CusUserUpdateReqVO updateReqVO);

    /**
     * 删除客户公司信息
     *
     * @param id 编号
     */
    void deleteCusUser(Long id);

    /**
     * 获得客户公司信息
     *
     * @param id 编号
     * @return 客户公司信息
     */
    CusUserDO getCusUser(Long id);

    /**
     * 获得客户公司信息列表
     *
     * @param ids 编号
     * @return 客户公司信息列表
     */
    List<CusUserDO> getCusUserList(Collection<Long> ids);

    /**
     * 获得客户公司信息分页
     *
     * @param pageReqVO 分页查询
     * @return 客户公司信息分页
     */
    PageResult<CusUserDO> getCusUserPage(CusUserPageReqVO pageReqVO);

    /**
     * 获得客户公司信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 客户公司信息列表
     */
    List<CusUserDO> getCusUserList(CusUserExportReqVO exportReqVO);

}
