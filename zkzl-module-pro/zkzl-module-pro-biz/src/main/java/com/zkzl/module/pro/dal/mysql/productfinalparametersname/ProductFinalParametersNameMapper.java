package com.zkzl.module.pro.dal.mysql.productfinalparametersname;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.productfinalparametersname.ProductFinalParametersNameDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.productfinalparametersname.vo.*;

/**
 * 产品固定参数名 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductFinalParametersNameMapper extends BaseMapperX<ProductFinalParametersNameDO> {

    default PageResult<ProductFinalParametersNameDO> selectPage(ProductFinalParametersNamePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductFinalParametersNameDO>()
                .likeIfPresent(ProductFinalParametersNameDO::getParameterCn, reqVO.getParameterCn())
                .likeIfPresent(ProductFinalParametersNameDO::getParameterEn, reqVO.getParameterEn())
                .orderByDesc(ProductFinalParametersNameDO::getId));
    }

    default List<ProductFinalParametersNameDO> selectList(ProductFinalParametersNameExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductFinalParametersNameDO>()
                .eqIfPresent(ProductFinalParametersNameDO::getParameterCn, reqVO.getParameterCn())
                .eqIfPresent(ProductFinalParametersNameDO::getParameterEn, reqVO.getParameterEn())
                .orderByDesc(ProductFinalParametersNameDO::getId));
    }

}
