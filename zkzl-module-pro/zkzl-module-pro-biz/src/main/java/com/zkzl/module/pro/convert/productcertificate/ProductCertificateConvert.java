package com.zkzl.module.pro.convert.productcertificate;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.productcertificate.vo.*;
import com.zkzl.module.pro.dal.dataobject.productcertificate.ProductCertificateDO;

/**
 * 证书 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface ProductCertificateConvert {

    ProductCertificateConvert INSTANCE = Mappers.getMapper(ProductCertificateConvert.class);

    ProductCertificateDO convert(ProductCertificateCreateReqVO bean);

    ProductCertificateDO convert(ProductCertificateUpdateReqVO bean);

    ProductCertificateRespVO convert(ProductCertificateDO bean);

    List<ProductCertificateRespVO> convertList(List<ProductCertificateDO> list);

    PageResult<ProductCertificateRespVO> convertPage(PageResult<ProductCertificateDO> page);

    List<ProductCertificateExcelVO> convertList02(List<ProductCertificateDO> list);

}
