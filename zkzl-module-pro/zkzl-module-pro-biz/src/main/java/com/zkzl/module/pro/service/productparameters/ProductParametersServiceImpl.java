package com.zkzl.module.pro.service.productparameters;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.productparameters.vo.*;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.productparameters.ProductParametersConvert;
import com.zkzl.module.pro.dal.mysql.productparameters.ProductParametersMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRODUCT_PARAMETERS_NOT_EXISTS;

/**
 * 产品参数 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductParametersServiceImpl implements ProductParametersService {

    @Resource
    private ProductParametersMapper ductParametersMapper;

    @Override
    public Long createductParameters(ProductParametersCreateReqVO createReqVO) {
        // 插入
        ProductParametersDO ductParameters = ProductParametersConvert.INSTANCE.convert(createReqVO);
        ductParametersMapper.insert(ductParameters);
        // 返回
        return ductParameters.getId();
    }

    @Override
    public void updateductParameters(ProductParametersUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductParametersExists(updateReqVO.getId());
        // 更新
        ProductParametersDO updateObj = ProductParametersConvert.INSTANCE.convert(updateReqVO);
        ductParametersMapper.updateById(updateObj);
    }

    @Override
    public void deleteductParameters(Long id) {
        // 校验存在
        this.validateductParametersExists(id);
        // 删除
        ductParametersMapper.deleteById(id);
    }

    private void validateductParametersExists(Long id) {
        if (ductParametersMapper.selectById(id) == null) {
            throw exception(PRODUCT_PARAMETERS_NOT_EXISTS);
        }
    }

    @Override
    public ProductParametersDO getductParameters(Long id) {
        return ductParametersMapper.selectById(id);
    }

    @Override
    public List<ProductParametersDO> getductParametersList(Collection<Long> ids) {
        return ductParametersMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductParametersDO> getductParametersPage(ProductParametersPageReqVO pageReqVO) {
        return ductParametersMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductParametersDO> getductParametersList(ProductParametersExportReqVO exportReqVO) {
        return ductParametersMapper.selectList(exportReqVO);
    }

}
