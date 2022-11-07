package com.zkzl.module.pro.service.productcertificate;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.productcertificate.vo.*;
import com.zkzl.module.pro.dal.dataobject.productcertificate.ProductCertificateDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.productcertificate.ProductCertificateConvert;
import com.zkzl.module.pro.dal.mysql.productcertificate.ProductCertificateMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.DUCT_CERTIFICATE_NOT_EXISTS;

/**
 * 证书 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductCertificateServiceImpl implements ProductCertificateService {

    @Resource
    private ProductCertificateMapper ductCertificateMapper;

    @Override
    public Long createductCertificate(ProductCertificateCreateReqVO createReqVO) {
        // 插入
        ProductCertificateDO ductCertificate = ProductCertificateConvert.INSTANCE.convert(createReqVO);
        ductCertificateMapper.insert(ductCertificate);
        // 返回
        return ductCertificate.getId();
    }

    @Override
    public void updateductCertificate(ProductCertificateUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductCertificateExists(updateReqVO.getId());
        // 更新
        ProductCertificateDO updateObj = ProductCertificateConvert.INSTANCE.convert(updateReqVO);
        ductCertificateMapper.updateById(updateObj);
    }

    @Override
    public void deleteductCertificate(Long id) {
        // 校验存在
        this.validateductCertificateExists(id);
        // 删除
        ductCertificateMapper.deleteById(id);
    }

    private void validateductCertificateExists(Long id) {
        if (ductCertificateMapper.selectById(id) == null) {
            throw exception(DUCT_CERTIFICATE_NOT_EXISTS);
        }
    }

    @Override
    public ProductCertificateDO getductCertificate(Long id) {
        return ductCertificateMapper.selectById(id);
    }

    @Override
    public List<ProductCertificateDO> getductCertificateList(Collection<Long> ids) {
        return ductCertificateMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductCertificateDO> getductCertificatePage(ProductCertificatePageReqVO pageReqVO) {
        return ductCertificateMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductCertificateDO> getductCertificateList(ProductCertificateExportReqVO exportReqVO) {
        return ductCertificateMapper.selectList(exportReqVO);
    }

}
