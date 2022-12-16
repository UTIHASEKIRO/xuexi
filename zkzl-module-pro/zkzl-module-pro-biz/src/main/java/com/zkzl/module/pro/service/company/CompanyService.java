package com.zkzl.module.pro.service.company;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.company.vo.*;
import com.zkzl.module.pro.dal.dataobject.company.CompanyDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 公司信息维护 Service 接口
 *
 * @author 芋道源码
 */
public interface CompanyService {

    /**
     * 创建公司信息维护
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCompany(@Valid CompanyCreateReqVO createReqVO);

    /**
     * 更新公司信息维护
     *
     * @param updateReqVO 更新信息
     */
    void updateCompany(@Valid CompanyUpdateReqVO updateReqVO);

    /**
     * 删除公司信息维护
     *
     * @param id 编号
     */
    void deleteCompany(Long id);

    /**
     * 获得公司信息维护
     *
     * @param id 编号
     * @return 公司信息维护
     */
    CompanyDO getCompany(Long id);

    /**
     * 获得公司信息维护列表
     *
     * @param ids 编号
     * @return 公司信息维护列表
     */
    List<CompanyDO> getCompanyList(Collection<Long> ids);

    /**
     * 获得公司信息维护分页
     *
     * @param pageReqVO 分页查询
     * @return 公司信息维护分页
     */
    PageResult<CompanyDO> getCompanyPage(CompanyPageReqVO pageReqVO);

    /**
     * 获得公司信息维护列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 公司信息维护列表
     */
    List<CompanyDO> getCompanyList(CompanyExportReqVO exportReqVO);

    CompanyRespVO getCompanyIntroduction();
}
