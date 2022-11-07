package com.zkzl.module.pro.service.productcertificate;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.productcertificate.vo.*;
import com.zkzl.module.pro.dal.dataobject.productcertificate.ProductCertificateDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 证书 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductCertificateService {

    /**
     * 创建证书
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createductCertificate(@Valid ProductCertificateCreateReqVO createReqVO);

    /**
     * 更新证书
     *
     * @param updateReqVO 更新信息
     */
    void updateductCertificate(@Valid ProductCertificateUpdateReqVO updateReqVO);

    /**
     * 删除证书
     *
     * @param id 编号
     */
    void deleteductCertificate(Long id);

    /**
     * 获得证书
     *
     * @param id 编号
     * @return 证书
     */
    ProductCertificateDO getductCertificate(Long id);

    /**
     * 获得证书列表
     *
     * @param ids 编号
     * @return 证书列表
     */
    List<ProductCertificateDO> getductCertificateList(Collection<Long> ids);

    /**
     * 获得证书分页
     *
     * @param pageReqVO 分页查询
     * @return 证书分页
     */
    PageResult<ProductCertificateDO> getductCertificatePage(ProductCertificatePageReqVO pageReqVO);

    /**
     * 获得证书列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 证书列表
     */
    List<ProductCertificateDO> getductCertificateList(ProductCertificateExportReqVO exportReqVO);

}
