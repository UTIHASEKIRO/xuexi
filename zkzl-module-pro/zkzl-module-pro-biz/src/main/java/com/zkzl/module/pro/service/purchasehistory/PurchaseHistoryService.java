package com.zkzl.module.pro.service.purchasehistory;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.purchasehistory.vo.*;
import com.zkzl.module.pro.dal.dataobject.purchasehistory.PurchaseHistoryDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 供货商供应记录 Service 接口
 *
 * @author 芋道源码
 */
public interface PurchaseHistoryService {

    /**
     * 创建供货商供应记录
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPurchaseHistory(@Valid PurchaseHistoryCreateReqVO createReqVO);

    /**
     * 更新供货商供应记录
     *
     * @param updateReqVO 更新信息
     */
    void updatePurchaseHistory(@Valid PurchaseHistoryUpdateReqVO updateReqVO);

    /**
     * 删除供货商供应记录
     *
     * @param id 编号
     */
    void deletePurchaseHistory(Long id);

    /**
     * 获得供货商供应记录
     *
     * @param id 编号
     * @return 供货商供应记录
     */
    PurchaseHistoryDO getPurchaseHistory(Long id);

    /**
     * 获得供货商供应记录列表
     *
     * @param ids 编号
     * @return 供货商供应记录列表
     */
    List<PurchaseHistoryDO> getPurchaseHistoryList(Collection<Long> ids);

    /**
     * 获得供货商供应记录分页
     *
     * @param pageReqVO 分页查询
     * @return 供货商供应记录分页
     */
    PageResult<PurchaseHistoryDO> getPurchaseHistoryPage(PurchaseHistoryPageReqVO pageReqVO);

    /**
     * 获得供货商供应记录列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 供货商供应记录列表
     */
    List<PurchaseHistoryDO> getPurchaseHistoryList(PurchaseHistoryExportReqVO exportReqVO);

}
