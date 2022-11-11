package com.zkzl.module.pro.service.company;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.company.vo.*;
import com.zkzl.module.pro.dal.dataobject.company.CompanyDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.company.CompanyConvert;
import com.zkzl.module.pro.dal.mysql.company.CompanyMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.COMPANY_NOT_EXISTS;

/**
 * 公司信息维护 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Override
    public Long createCompany(CompanyCreateReqVO createReqVO) {
        // 插入
        CompanyDO company = CompanyConvert.INSTANCE.convert(createReqVO);
        companyMapper.insert(company);
        // 返回
        return company.getId();
    }

    @Override
    public void updateCompany(CompanyUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCompanyExists(updateReqVO.getId());
        // 更新
        CompanyDO updateObj = CompanyConvert.INSTANCE.convert(updateReqVO);
        companyMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompany(Long id) {
        // 校验存在
        this.validateCompanyExists(id);
        // 删除
        companyMapper.deleteById(id);
    }

    private void validateCompanyExists(Long id) {
        if (companyMapper.selectById(id) == null) {
            throw exception(COMPANY_NOT_EXISTS);
        }
    }

    @Override
    public CompanyDO getCompany(Long id) {
        return companyMapper.selectById(id);
    }

    @Override
    public List<CompanyDO> getCompanyList(Collection<Long> ids) {
        return companyMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CompanyDO> getCompanyPage(CompanyPageReqVO pageReqVO) {
        return companyMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CompanyDO> getCompanyList(CompanyExportReqVO exportReqVO) {
        return companyMapper.selectList(exportReqVO);
    }

}
