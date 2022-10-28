package com.zkzl.module.pro.service.evidence;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.evidence.vo.*;
import com.zkzl.module.pro.dal.dataobject.evidence.EvidenceDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.evidence.EvidenceConvert;
import com.zkzl.module.pro.dal.mysql.evidence.EvidenceMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.EVIDENCE_NOT_EXISTS;

/**
 * 订单凭证信息 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class EvidenceServiceImpl implements EvidenceService {

    @Resource
    private EvidenceMapper evidenceMapper;

    @Override
    public Long createEvidence(EvidenceCreateReqVO createReqVO) {
        // 插入
        EvidenceDO evidence = EvidenceConvert.INSTANCE.convert(createReqVO);
        evidenceMapper.insert(evidence);
        // 返回
        return evidence.getId();
    }

    @Override
    public void updateEvidence(EvidenceUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateEvidenceExists(updateReqVO.getId());
        // 更新
        EvidenceDO updateObj = EvidenceConvert.INSTANCE.convert(updateReqVO);
        evidenceMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvidence(Long id) {
        // 校验存在
        this.validateEvidenceExists(id);
        // 删除
        evidenceMapper.deleteById(id);
    }

    private void validateEvidenceExists(Long id) {
        if (evidenceMapper.selectById(id) == null) {
            throw exception(EVIDENCE_NOT_EXISTS);
        }
    }

    @Override
    public EvidenceDO getEvidence(Long id) {
        return evidenceMapper.selectById(id);
    }

    @Override
    public List<EvidenceDO> getEvidenceList(Collection<Long> ids) {
        return evidenceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EvidenceDO> getEvidencePage(EvidencePageReqVO pageReqVO) {
        return evidenceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EvidenceDO> getEvidenceList(EvidenceExportReqVO exportReqVO) {
        return evidenceMapper.selectList(exportReqVO);
    }

}
