package com.zkzl.module.pro.dal.mysql.purchasehistory;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.purchasehistory.PurchaseHistoryDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.purchasehistory.vo.*;

/**
 * 供货商供应记录 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PurchaseHistoryMapper extends BaseMapperX<PurchaseHistoryDO> {

    default PageResult<PurchaseHistoryDO> selectPage(PurchaseHistoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PurchaseHistoryDO>()
                .eqIfPresent(PurchaseHistoryDO::getPurchaseHistoryId, reqVO.getPurchaseHistoryId())
                .likeIfPresent(PurchaseHistoryDO::getSupplyCompany, reqVO.getSupplyCompany())
                .eqIfPresent(PurchaseHistoryDO::getProductSerial, reqVO.getProductSerial())
                .eqIfPresent(PurchaseHistoryDO::getHsSerial, reqVO.getHsSerial())
                .eqIfPresent(PurchaseHistoryDO::getProductSize, reqVO.getProductSize())
                .eqIfPresent(PurchaseHistoryDO::getProductColor, reqVO.getProductColor())
                .eqIfPresent(PurchaseHistoryDO::getProductG, reqVO.getProductG())
                .eqIfPresent(PurchaseHistoryDO::getBoxLength, reqVO.getBoxLength())
                .eqIfPresent(PurchaseHistoryDO::getBoxWide, reqVO.getBoxWide())
                .eqIfPresent(PurchaseHistoryDO::getBoxHeight, reqVO.getBoxHeight())
                .eqIfPresent(PurchaseHistoryDO::getUnitPrice, reqVO.getUnitPrice())
                .eqIfPresent(PurchaseHistoryDO::getGrossWeight, reqVO.getGrossWeight())
                .eqIfPresent(PurchaseHistoryDO::getNetWeight, reqVO.getNetWeight())
                .betweenIfPresent(PurchaseHistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PurchaseHistoryDO::getId));
    }

    default List<PurchaseHistoryDO> selectList(PurchaseHistoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PurchaseHistoryDO>()
                .eqIfPresent(PurchaseHistoryDO::getPurchaseHistoryId, reqVO.getPurchaseHistoryId())
                .eqIfPresent(PurchaseHistoryDO::getProductSerial, reqVO.getProductSerial())
                .eqIfPresent(PurchaseHistoryDO::getHsSerial, reqVO.getHsSerial())
                .eqIfPresent(PurchaseHistoryDO::getProductSize, reqVO.getProductSize())
                .eqIfPresent(PurchaseHistoryDO::getProductColor, reqVO.getProductColor())
                .eqIfPresent(PurchaseHistoryDO::getProductG, reqVO.getProductG())
                .eqIfPresent(PurchaseHistoryDO::getBoxLength, reqVO.getBoxLength())
                .eqIfPresent(PurchaseHistoryDO::getBoxWide, reqVO.getBoxWide())
                .eqIfPresent(PurchaseHistoryDO::getBoxHeight, reqVO.getBoxHeight())
                .eqIfPresent(PurchaseHistoryDO::getUnitPrice, reqVO.getUnitPrice())
                .eqIfPresent(PurchaseHistoryDO::getGrossWeight, reqVO.getGrossWeight())
                .eqIfPresent(PurchaseHistoryDO::getNetWeight, reqVO.getNetWeight())
                .betweenIfPresent(PurchaseHistoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PurchaseHistoryDO::getId));
    }

}
