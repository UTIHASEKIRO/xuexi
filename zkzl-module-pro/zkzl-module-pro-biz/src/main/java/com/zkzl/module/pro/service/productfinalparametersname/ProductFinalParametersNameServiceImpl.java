package com.zkzl.module.pro.service.productfinalparametersname;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.productfinalparametersname.vo.*;
import com.zkzl.module.pro.dal.dataobject.productfinalparametersname.ProductFinalParametersNameDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.productfinalparametersname.ProductFinalParametersNameConvert;
import com.zkzl.module.pro.dal.mysql.productfinalparametersname.ProductFinalParametersNameMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.DUCT_FINAL_PARAMETERS_NAME_NOT_EXISTS;

/**
 * 产品固定参数名 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductFinalParametersNameServiceImpl implements ProductFinalParametersNameService {

    @Resource
    private ProductFinalParametersNameMapper ductFinalParametersNameMapper;

    @Override
    public Long createductFinalParametersName(ProductFinalParametersNameCreateReqVO createReqVO) {
        // 插入
        ProductFinalParametersNameDO ductFinalParametersName = ProductFinalParametersNameConvert.INSTANCE.convert(createReqVO);
        ductFinalParametersNameMapper.insert(ductFinalParametersName);
        // 返回
        return ductFinalParametersName.getId();
    }

    @Override
    public void updateductFinalParametersName(ProductFinalParametersNameUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductFinalParametersNameExists(updateReqVO.getId());
        // 更新
        ProductFinalParametersNameDO updateObj = ProductFinalParametersNameConvert.INSTANCE.convert(updateReqVO);
        ductFinalParametersNameMapper.updateById(updateObj);
    }

    @Override
    public void deleteductFinalParametersName(Long id) {
        // 校验存在
        this.validateductFinalParametersNameExists(id);
        // 删除
        ductFinalParametersNameMapper.deleteById(id);
    }

    private void validateductFinalParametersNameExists(Long id) {
        if (ductFinalParametersNameMapper.selectById(id) == null) {
            throw exception(DUCT_FINAL_PARAMETERS_NAME_NOT_EXISTS);
        }
    }

    @Override
    public ProductFinalParametersNameDO getductFinalParametersName(Long id) {
        return ductFinalParametersNameMapper.selectById(id);
    }

    @Override
    public List<ProductFinalParametersNameDO> getductFinalParametersNameList(Collection<Long> ids) {
        return ductFinalParametersNameMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductFinalParametersNameDO> getductFinalParametersNamePage(ProductFinalParametersNamePageReqVO pageReqVO) {
        return ductFinalParametersNameMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductFinalParametersNameDO> getductFinalParametersNameList(ProductFinalParametersNameExportReqVO exportReqVO) {
        return ductFinalParametersNameMapper.selectList(exportReqVO);
    }

}
