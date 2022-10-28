package com.zkzl.module.pro.dal.mysql.productpic;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.productpic.vo.*;

/**
 * 产品图片 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductPicMapper extends BaseMapperX<ProductPicDO> {

    default PageResult<ProductPicDO> selectPage(ProductPicPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductPicDO>()
                .eqIfPresent(ProductPicDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductPicDO::getUrl, reqVO.getUrl())
                .orderByDesc(ProductPicDO::getId));
    }

    default List<ProductPicDO> selectList(ProductPicExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductPicDO>()
                .eqIfPresent(ProductPicDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductPicDO::getUrl, reqVO.getUrl())
                .orderByDesc(ProductPicDO::getId));
    }

}
