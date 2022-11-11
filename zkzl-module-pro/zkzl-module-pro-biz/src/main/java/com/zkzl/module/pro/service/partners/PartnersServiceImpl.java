package com.zkzl.module.pro.service.partners;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.partners.vo.*;
import com.zkzl.module.pro.dal.dataobject.partners.PartnersDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.partners.PartnersConvert;
import com.zkzl.module.pro.dal.mysql.partners.PartnersMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PARTNERS_NOT_EXISTS;

/**
 * 合作伙伴 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PartnersServiceImpl implements PartnersService {

    @Resource
    private PartnersMapper partnersMapper;

    @Override
    public Long createPartners(PartnersCreateReqVO createReqVO) {
        // 插入
        PartnersDO partners = PartnersConvert.INSTANCE.convert(createReqVO);
        partnersMapper.insert(partners);
        // 返回
        return partners.getId();
    }

    @Override
    public void updatePartners(PartnersUpdateReqVO updateReqVO) {
        // 校验存在
        this.validatePartnersExists(updateReqVO.getId());
        // 更新
        PartnersDO updateObj = PartnersConvert.INSTANCE.convert(updateReqVO);
        partnersMapper.updateById(updateObj);
    }

    @Override
    public void deletePartners(Long id) {
        // 校验存在
        this.validatePartnersExists(id);
        // 删除
        partnersMapper.deleteById(id);
    }

    private void validatePartnersExists(Long id) {
        if (partnersMapper.selectById(id) == null) {
            throw exception(PARTNERS_NOT_EXISTS);
        }
    }

    @Override
    public PartnersDO getPartners(Long id) {
        return partnersMapper.selectById(id);
    }

    @Override
    public List<PartnersDO> getPartnersList(Collection<Long> ids) {
        return partnersMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PartnersDO> getPartnersPage(PartnersPageReqVO pageReqVO) {
        return partnersMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PartnersDO> getPartnersList(PartnersExportReqVO exportReqVO) {
        return partnersMapper.selectList(exportReqVO);
    }

}
