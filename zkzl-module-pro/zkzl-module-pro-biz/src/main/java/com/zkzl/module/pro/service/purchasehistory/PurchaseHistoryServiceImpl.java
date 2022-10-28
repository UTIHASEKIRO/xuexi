package com.zkzl.module.pro.service.purchasehistory;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.purchasehistory.vo.*;
import com.zkzl.module.pro.dal.dataobject.purchasehistory.PurchaseHistoryDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.purchasehistory.PurchaseHistoryConvert;
import com.zkzl.module.pro.dal.mysql.purchasehistory.PurchaseHistoryMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PURCHASE_HISTORY_NOT_EXISTS;

/**
 * 供货商供应记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

    @Resource
    private PurchaseHistoryMapper purchaseHistoryMapper;

    @Override
    public Long createPurchaseHistory(PurchaseHistoryCreateReqVO createReqVO) {
        // 插入
        PurchaseHistoryDO purchaseHistory = PurchaseHistoryConvert.INSTANCE.convert(createReqVO);
        purchaseHistoryMapper.insert(purchaseHistory);
        // 返回
        return purchaseHistory.getId();
    }

    @Override
    public void updatePurchaseHistory(PurchaseHistoryUpdateReqVO updateReqVO) {
        // 校验存在
        this.validatePurchaseHistoryExists(updateReqVO.getId());
        // 更新
        PurchaseHistoryDO updateObj = PurchaseHistoryConvert.INSTANCE.convert(updateReqVO);
        purchaseHistoryMapper.updateById(updateObj);
    }

    @Override
    public void deletePurchaseHistory(Long id) {
        // 校验存在
        this.validatePurchaseHistoryExists(id);
        // 删除
        purchaseHistoryMapper.deleteById(id);
    }

    private void validatePurchaseHistoryExists(Long id) {
        if (purchaseHistoryMapper.selectById(id) == null) {
            throw exception(PURCHASE_HISTORY_NOT_EXISTS);
        }
    }

    @Override
    public PurchaseHistoryDO getPurchaseHistory(Long id) {
        return purchaseHistoryMapper.selectById(id);
    }

    @Override
    public List<PurchaseHistoryDO> getPurchaseHistoryList(Collection<Long> ids) {
        return purchaseHistoryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<PurchaseHistoryDO> getPurchaseHistoryPage(PurchaseHistoryPageReqVO pageReqVO) {
        return purchaseHistoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PurchaseHistoryDO> getPurchaseHistoryList(PurchaseHistoryExportReqVO exportReqVO) {
        return purchaseHistoryMapper.selectList(exportReqVO);
    }

}
