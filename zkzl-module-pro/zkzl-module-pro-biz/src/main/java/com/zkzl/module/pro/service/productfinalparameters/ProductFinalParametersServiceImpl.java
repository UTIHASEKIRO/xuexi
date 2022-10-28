package com.zkzl.module.pro.service.productfinalparameters;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.productfinalparameters.vo.*;
import com.zkzl.module.pro.dal.dataobject.productfinalparameters.ProductFinalParametersDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.productfinalparameters.ProductFinalParametersConvert;
import com.zkzl.module.pro.dal.mysql.productfinalparameters.ProductFinalParametersMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRODUCT_FINAL_PARAMETERS_NOT_EXISTS;

/**
 * 产品固定参数 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductFinalParametersServiceImpl implements ProductFinalParametersService {

    @Resource
    private ProductFinalParametersMapper ductFinalParametersMapper;

    @Override
    public Long createductFinalParameters(ProductFinalParametersCreateReqVO createReqVO) {
        // 插入
        ProductFinalParametersDO ductFinalParameters = ProductFinalParametersConvert.INSTANCE.convert(createReqVO);
        ductFinalParametersMapper.insert(ductFinalParameters);
        // 返回
        return ductFinalParameters.getId();
    }

    @Override
    public void updateductFinalParameters(ProductFinalParametersUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductFinalParametersExists(updateReqVO.getId());
        // 更新
        ProductFinalParametersDO updateObj = ProductFinalParametersConvert.INSTANCE.convert(updateReqVO);
        ductFinalParametersMapper.updateById(updateObj);
    }

    @Override
    public void deleteductFinalParameters(Long id) {
        // 校验存在
        this.validateductFinalParametersExists(id);
        // 删除
        ductFinalParametersMapper.deleteById(id);
    }

    private void validateductFinalParametersExists(Long id) {
        if (ductFinalParametersMapper.selectById(id) == null) {
            throw exception(PRODUCT_FINAL_PARAMETERS_NOT_EXISTS);
        }
    }

    @Override
    public ProductFinalParametersDO getductFinalParameters(Long id) {
        return ductFinalParametersMapper.selectById(id);
    }

    @Override
    public List<ProductFinalParametersDO> getductFinalParametersList(Collection<Long> ids) {
        return ductFinalParametersMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductFinalParametersDO> getductFinalParametersPage(ProductFinalParametersPageReqVO pageReqVO) {
        return ductFinalParametersMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductFinalParametersDO> getductFinalParametersList(ProductFinalParametersExportReqVO exportReqVO) {
        return ductFinalParametersMapper.selectList(exportReqVO);
    }

}
