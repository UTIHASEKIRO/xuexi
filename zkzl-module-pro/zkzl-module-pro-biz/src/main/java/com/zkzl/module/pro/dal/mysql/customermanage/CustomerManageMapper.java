package com.zkzl.module.pro.dal.mysql.customermanage;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.customermanage.CustomerManageDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.customermanage.vo.*;

/**
 * 客户管理跟进 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerManageMapper extends BaseMapperX<CustomerManageDO> {

    default PageResult<CustomerManageDO> selectPage(CustomerManagePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CustomerManageDO>()
                .eqIfPresent(CustomerManageDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CustomerManageDO::getImportantLevel, reqVO.getImportantLevel())
                .eqIfPresent(CustomerManageDO::getCustomerIntention, reqVO.getCustomerIntention())
                .eqIfPresent(CustomerManageDO::getCustomerDemand, reqVO.getCustomerDemand())
                .eqIfPresent(CustomerManageDO::getCustomerResult, reqVO.getCustomerResult())
                .eqIfPresent(CustomerManageDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CustomerManageDO::getFollowNum, reqVO.getFollowNum())
                .eqIfPresent(CustomerManageDO::getFollowUserId, reqVO.getFollowUserId())
                .betweenIfPresent(CustomerManageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerManageDO::getUserId));
    }

    default List<CustomerManageDO> selectList(CustomerManageExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CustomerManageDO>()
                .eqIfPresent(CustomerManageDO::getUserId, reqVO.getUserId())
                .eqIfPresent(CustomerManageDO::getImportantLevel, reqVO.getImportantLevel())
                .eqIfPresent(CustomerManageDO::getCustomerIntention, reqVO.getCustomerIntention())
                .eqIfPresent(CustomerManageDO::getCustomerDemand, reqVO.getCustomerDemand())
                .eqIfPresent(CustomerManageDO::getCustomerResult, reqVO.getCustomerResult())
                .eqIfPresent(CustomerManageDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CustomerManageDO::getFollowNum, reqVO.getFollowNum())
                .eqIfPresent(CustomerManageDO::getFollowUserId, reqVO.getFollowUserId())
                .betweenIfPresent(CustomerManageDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CustomerManageDO::getUserId));
    }
    /*查询客户跟进信息*/
    IPage<CustomerManageDO> pageCustFollow(IPage<CustomerManageDO> page, CustomerManagePageReqVO pageVO);
}
