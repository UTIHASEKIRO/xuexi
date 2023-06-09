package com.zkzl.module.product.dal.mysql.brand;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.product.dal.dataobject.brand.BrandDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.product.controller.admin.brand.vo.*;

/**
 * 品牌 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BrandMapper extends BaseMapperX<BrandDO> {

    default PageResult<BrandDO> selectPage(BrandPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BrandDO>()
                .eqIfPresent(BrandDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(BrandDO::getName, reqVO.getName())
                .eqIfPresent(BrandDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BrandDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BrandDO::getId));
    }

    default List<BrandDO> selectList(BrandExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BrandDO>()
                .eqIfPresent(BrandDO::getCategoryId, reqVO.getCategoryId())
                .likeIfPresent(BrandDO::getName, reqVO.getName())
                .eqIfPresent(BrandDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BrandDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BrandDO::getId));
    }

}
