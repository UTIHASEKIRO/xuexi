package com.zkzl.module.pro.service.partners;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.partners.vo.*;
import com.zkzl.module.pro.dal.dataobject.partners.PartnersDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 合作伙伴 Service 接口
 *
 * @author 芋道源码
 */
public interface PartnersService {

    /**
     * 创建合作伙伴
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPartners(@Valid PartnersCreateReqVO createReqVO);

    /**
     * 更新合作伙伴
     *
     * @param updateReqVO 更新信息
     */
    void updatePartners(@Valid PartnersUpdateReqVO updateReqVO);

    /**
     * 删除合作伙伴
     *
     * @param id 编号
     */
    void deletePartners(Long id);

    /**
     * 获得合作伙伴
     *
     * @param id 编号
     * @return 合作伙伴
     */
    PartnersDO getPartners(Long id);

    /**
     * 获得合作伙伴列表
     *
     * @param ids 编号
     * @return 合作伙伴列表
     */
    List<PartnersDO> getPartnersList(Collection<Long> ids);

    /**
     * 获得合作伙伴分页
     *
     * @param pageReqVO 分页查询
     * @return 合作伙伴分页
     */
    PageResult<PartnersDO> getPartnersPage(PartnersPageReqVO pageReqVO);

    /**
     * 获得合作伙伴列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 合作伙伴列表
     */
    List<PartnersDO> getPartnersList(PartnersExportReqVO exportReqVO);

}
