package com.zkzl.module.pro.service.evidence;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import com.zkzl.module.pro.controller.admin.evidence.vo.EvidenceCreateReqVO;
import com.zkzl.module.pro.controller.admin.evidence.vo.EvidenceExportReqVO;
import com.zkzl.module.pro.controller.admin.evidence.vo.EvidencePageReqVO;
import com.zkzl.module.pro.controller.admin.evidence.vo.EvidenceUpdateReqVO;
import com.zkzl.module.pro.convert.evidence.EvidenceConvert;
import com.zkzl.module.pro.dal.dataobject.evidence.EvidenceDO;
import com.zkzl.module.pro.dal.mysql.evidence.EvidenceMapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

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
        evidence.setEvidenceId(IdUtil.getSnowflakeNextIdStr());//业务id
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
        return evidenceMapper.getEvidence(id);
    }

    @Override
    public List<EvidenceDO> getEvidenceList(Collection<Long> ids) {
        return evidenceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EvidenceDO> getEvidencePage(EvidencePageReqVO pageReqVO) {
        IPage<EvidenceDO> mpPage = MyBatisUtils.buildPage(pageReqVO);
        evidenceMapper.getEvidencePage(mpPage,pageReqVO);
        return new PageResult<>(mpPage.getRecords(),mpPage.getTotal());
    }

    @Override
    public List<EvidenceDO> getEvidenceList(EvidenceExportReqVO exportReqVO) {
        return evidenceMapper.selectList(exportReqVO);
    }

    @Override
    public int updateByOrder(EvidenceCreateReqVO param) {
        return evidenceMapper.update(null,new LambdaUpdateWrapper<EvidenceDO>()
                .set(EvidenceDO::getBalancePic,param.getBalancePic())
                .eq(EvidenceDO::getOrderId,param.getOrderId())
                .eq(EvidenceDO::getPriceInquryId,param.getPriceInquryId()));
    }

    @Override
    public EvidenceDO getOne(EvidenceExportReqVO query) {
        return evidenceMapper.selectOne(new LambdaQueryWrapperX<EvidenceDO>()
                .eqIfPresent(EvidenceDO::getOrderId, query.getOrderId())
                .eqIfPresent(EvidenceDO::getPriceInquryId, query.getPriceInquryId())
                .last("limit 1")
        );
    }

    @Override
    public void updateById(EvidenceDO evidenceDO) {
        evidenceMapper.updateById(evidenceDO);
    }

    @Override
    public void depositPicRejected(Long id) {
        evidenceMapper.update(null,new LambdaUpdateWrapper<EvidenceDO>().set(EvidenceDO::getDepositPic,null)
        .eq(EvidenceDO::getId,id)
        );
    }

    @Override
    public void balancePicRejected(Long id) {
        evidenceMapper.update(null,new LambdaUpdateWrapper<EvidenceDO>().set(EvidenceDO::getBalancePic,null)
                .eq(EvidenceDO::getId,id)
        );
    }

}
