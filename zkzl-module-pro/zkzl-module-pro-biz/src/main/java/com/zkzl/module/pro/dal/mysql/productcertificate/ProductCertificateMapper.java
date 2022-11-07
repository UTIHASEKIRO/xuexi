package com.zkzl.module.pro.dal.mysql.productcertificate;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.productcertificate.ProductCertificateDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.productcertificate.vo.*;

/**
 * 证书 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductCertificateMapper extends BaseMapperX<ProductCertificateDO> {

    default PageResult<ProductCertificateDO> selectPage(ProductCertificatePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProductCertificateDO>()
                .eqIfPresent(ProductCertificateDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductCertificateDO::getCertificateUrl, reqVO.getCertificateUrl())
                .orderByDesc(ProductCertificateDO::getId));
    }

    default List<ProductCertificateDO> selectList(ProductCertificateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ProductCertificateDO>()
                .eqIfPresent(ProductCertificateDO::getProductId, reqVO.getProductId())
                .eqIfPresent(ProductCertificateDO::getCertificateUrl, reqVO.getCertificateUrl())
                .orderByDesc(ProductCertificateDO::getId));
    }

}
