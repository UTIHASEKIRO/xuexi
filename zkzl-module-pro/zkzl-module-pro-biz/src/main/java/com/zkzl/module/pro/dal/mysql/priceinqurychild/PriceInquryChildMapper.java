package com.zkzl.module.pro.dal.mysql.priceinqurychild;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.priceinqurychild.PriceInquryChildDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.*;

/**
 * 询价表子 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PriceInquryChildMapper extends BaseMapperX<PriceInquryChildDO> {

    default PageResult<PriceInquryChildDO> selectPage(PriceInquryChildPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PriceInquryChildDO>()
                .eqIfPresent(PriceInquryChildDO::getPriceInquryId, reqVO.getPriceInquryId())
                .eqIfPresent(PriceInquryChildDO::getProductId, reqVO.getProductId())
                .eqIfPresent(PriceInquryChildDO::getHsSerial, reqVO.getHsSerial())
                .eqIfPresent(PriceInquryChildDO::getProductDesc, reqVO.getProductDesc())
                .eqIfPresent(PriceInquryChildDO::getProductSize, reqVO.getProductSize())
                .eqIfPresent(PriceInquryChildDO::getProductColor, reqVO.getProductColor())
                .eqIfPresent(PriceInquryChildDO::getProductG, reqVO.getProductG())
                .eqIfPresent(PriceInquryChildDO::getPackageWay, reqVO.getPackageWay())
                .eqIfPresent(PriceInquryChildDO::getBoxLength, reqVO.getBoxLength())
                .eqIfPresent(PriceInquryChildDO::getBoxHeight, reqVO.getBoxHeight())
                .eqIfPresent(PriceInquryChildDO::getBoxWide, reqVO.getBoxWide())
                .eqIfPresent(PriceInquryChildDO::getVolume, reqVO.getVolume())
                .eqIfPresent(PriceInquryChildDO::getPrice, reqVO.getPrice())
                .eqIfPresent(PriceInquryChildDO::getGrossWeight, reqVO.getGrossWeight())
                .eqIfPresent(PriceInquryChildDO::getNetWeight, reqVO.getNetWeight())
                .eqIfPresent(PriceInquryChildDO::getMount, reqVO.getMount())
                .eqIfPresent(PriceInquryChildDO::getUnitPrice, reqVO.getUnitPrice())
                .eqIfPresent(PriceInquryChildDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(PriceInquryChildDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PriceInquryChildDO::getId));
    }

    default List<PriceInquryChildDO> selectList(PriceInquryChildExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PriceInquryChildDO>()
                .eqIfPresent(PriceInquryChildDO::getPriceInquryId, reqVO.getPriceInquryId())
                .eqIfPresent(PriceInquryChildDO::getProductId, reqVO.getProductId())
                .eqIfPresent(PriceInquryChildDO::getHsSerial, reqVO.getHsSerial())
                .eqIfPresent(PriceInquryChildDO::getProductDesc, reqVO.getProductDesc())
                .eqIfPresent(PriceInquryChildDO::getProductSize, reqVO.getProductSize())
                .eqIfPresent(PriceInquryChildDO::getProductColor, reqVO.getProductColor())
                .eqIfPresent(PriceInquryChildDO::getProductG, reqVO.getProductG())
                .eqIfPresent(PriceInquryChildDO::getPackageWay, reqVO.getPackageWay())
                .eqIfPresent(PriceInquryChildDO::getBoxLength, reqVO.getBoxLength())
                .eqIfPresent(PriceInquryChildDO::getBoxHeight, reqVO.getBoxHeight())
                .eqIfPresent(PriceInquryChildDO::getBoxWide, reqVO.getBoxWide())
                .eqIfPresent(PriceInquryChildDO::getVolume, reqVO.getVolume())
                .eqIfPresent(PriceInquryChildDO::getPrice, reqVO.getPrice())
                .eqIfPresent(PriceInquryChildDO::getGrossWeight, reqVO.getGrossWeight())
                .eqIfPresent(PriceInquryChildDO::getNetWeight, reqVO.getNetWeight())
                .eqIfPresent(PriceInquryChildDO::getMount, reqVO.getMount())
                .eqIfPresent(PriceInquryChildDO::getUnitPrice, reqVO.getUnitPrice())
                .eqIfPresent(PriceInquryChildDO::getRemark, reqVO.getRemark())
                .betweenIfPresent(PriceInquryChildDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PriceInquryChildDO::getId));
    }

}
