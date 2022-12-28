package com.zkzl.module.pro.service.evidence;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.evidence.vo.*;
import com.zkzl.module.pro.dal.dataobject.evidence.EvidenceDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 订单凭证信息 Service 接口
 *
 * @author 芋道源码
 */
public interface EvidenceService {

    /**
     * 创建订单凭证信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvidence(@Valid EvidenceCreateReqVO createReqVO);

    /**
     * 更新订单凭证信息
     *
     * @param updateReqVO 更新信息
     */
    void updateEvidence(@Valid EvidenceUpdateReqVO updateReqVO);

    /**
     * 删除订单凭证信息
     *
     * @param id 编号
     */
    void deleteEvidence(Long id);

    /**
     * 获得订单凭证信息
     *
     * @param id 编号
     * @return 订单凭证信息
     */
    EvidenceDO getEvidence(Long id);

    /**
     * 获得订单凭证信息列表
     *
     * @param ids 编号
     * @return 订单凭证信息列表
     */
    List<EvidenceDO> getEvidenceList(Collection<Long> ids);

    /**
     * 获得订单凭证信息分页
     *
     * @param pageReqVO 分页查询
     * @return 订单凭证信息分页
     */
    PageResult<EvidenceDO> getEvidencePage(EvidencePageReqVO pageReqVO);

    /**
     * 获得订单凭证信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 订单凭证信息列表
     */
    List<EvidenceDO> getEvidenceList(EvidenceExportReqVO exportReqVO);

    /*上传尾款截图*/
    int updateByOrder(EvidenceCreateReqVO param);
}
