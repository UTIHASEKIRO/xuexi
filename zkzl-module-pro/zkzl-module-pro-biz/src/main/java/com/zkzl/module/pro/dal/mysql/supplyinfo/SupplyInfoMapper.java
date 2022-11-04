package com.zkzl.module.pro.dal.mysql.supplyinfo;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.controller.admin.priceinqury.vo.PriceInquryChildsSupplyerVO;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;
import com.zkzl.module.pro.dal.dataobject.supplyinfo.SupplyInfoDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.supplyinfo.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 供货商信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface SupplyInfoMapper extends BaseMapperX<SupplyInfoDO> {

    default PageResult<SupplyInfoDO> selectPage(SupplyInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SupplyInfoDO>()
                .likeIfPresent(SupplyInfoDO::getName, reqVO.getName())
                .eqIfPresent(SupplyInfoDO::getAddress, reqVO.getAddress())
                .eqIfPresent(SupplyInfoDO::getContact, reqVO.getContact())
                .eqIfPresent(SupplyInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(SupplyInfoDO::getEmail, reqVO.getEmail())
                .betweenIfPresent(SupplyInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SupplyInfoDO::getProductId, reqVO.getProductId())
                .eqIfPresent(SupplyInfoDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(SupplyInfoDO::getReasonPrice, reqVO.getReasonPrice())
                .eqIfPresent(SupplyInfoDO::getReasonQuality, reqVO.getReasonQuality())
                .eqIfPresent(SupplyInfoDO::getReasonService, reqVO.getReasonService())
                .orderByDesc(SupplyInfoDO::getId));
    }

    default List<SupplyInfoDO> selectList(SupplyInfoExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<SupplyInfoDO>()
                .likeIfPresent(SupplyInfoDO::getName, reqVO.getName())
                .eqIfPresent(SupplyInfoDO::getAddress, reqVO.getAddress())
                .eqIfPresent(SupplyInfoDO::getContact, reqVO.getContact())
                .eqIfPresent(SupplyInfoDO::getPhone, reqVO.getPhone())
                .eqIfPresent(SupplyInfoDO::getEmail, reqVO.getEmail())
                .betweenIfPresent(SupplyInfoDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SupplyInfoDO::getProductId, reqVO.getProductId())
                .eqIfPresent(SupplyInfoDO::getTypeId, reqVO.getTypeId())
                .eqIfPresent(SupplyInfoDO::getReasonPrice, reqVO.getReasonPrice())
                .eqIfPresent(SupplyInfoDO::getReasonQuality, reqVO.getReasonQuality())
                .eqIfPresent(SupplyInfoDO::getReasonService, reqVO.getReasonService())
                .orderByDesc(SupplyInfoDO::getId));
    }

    /*获得供货商信息-产品类别*/
    List<ProductTypeDO> getProductType();

    /*供货商信息分页查询*/
    IPage<SupplyInfoPageVO> pageSupplyInfo(@Param("page") IPage<SupplyInfoPageVO> page, @Param("param") SupplyInfoPageReqVO pageReqVO);

    /*获得供货商名称*/
    IPage<SupplyInfoNameVO> getSupplyInfoName(@Param("page")IPage<SupplyInfoNameVO> mPage,@Param("param") SupplyInfoPageReqVO pageVO);

    /*根据产品id查询供应商列表*/
    List<PriceInquryChildsSupplyerVO> getSupplyByProductId(String productId);
}
