package com.zkzl.module.pay.dal.mysql.merchant;

import com.zkzl.module.pay.controller.admin.merchant.vo.merchant.PayMerchantExportReqVO;
import com.zkzl.module.pay.controller.admin.merchant.vo.merchant.PayMerchantPageReqVO;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.framework.mybatis.core.query.QueryWrapperX;
import com.zkzl.module.pay.dal.dataobject.merchant.PayMerchantDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMerchantMapper extends BaseMapperX<PayMerchantDO> {

    default PageResult<PayMerchantDO> selectPage(PayMerchantPageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<PayMerchantDO>()
                .likeIfPresent("no", reqVO.getNo())
                .likeIfPresent("name", reqVO.getName())
                .likeIfPresent("short_name", reqVO.getShortName())
                .eqIfPresent("status", reqVO.getStatus())
                .eqIfPresent("remark", reqVO.getRemark())
                .betweenIfPresent("create_time", reqVO.getCreateTime())
                .orderByDesc("id"));
    }

    default List<PayMerchantDO> selectList(PayMerchantExportReqVO reqVO) {
        return selectList(new QueryWrapperX<PayMerchantDO>()
                .likeIfPresent("no", reqVO.getNo())
                .likeIfPresent("name", reqVO.getName())
                .likeIfPresent("short_name", reqVO.getShortName())
                .eqIfPresent("status", reqVO.getStatus())
                .eqIfPresent("remark", reqVO.getRemark())
                .betweenIfPresent("create_time", reqVO.getCreateTime())
                .orderByDesc("id"));
    }

    /**
     * 根据商户名称模糊查询商户集合
     *
     * @param merchantName 商户名称
     * @return 商户集合
     */
    default List<PayMerchantDO> getMerchantListByName(String merchantName) {
        return this.selectList(new QueryWrapperX<PayMerchantDO>()
                .likeIfPresent("name", merchantName));
    }
}
