package com.zkzl.module.pro.service.producttype;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.producttype.vo.*;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.producttype.ProductTypeConvert;
import com.zkzl.module.pro.dal.mysql.producttype.ProductTypeMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRODUCT_TYPE_NOT_EXISTS;

/**
 * 商品类别 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductTypeServiceImpl implements ProductTypeService {

    @Resource
    private ProductTypeMapper ductTypeMapper;

    @Override
    public Long createductType(ProductTypeCreateReqVO createReqVO) {
        // 插入
        ProductTypeDO ductType = ProductTypeConvert.INSTANCE.convert(createReqVO);
        ductTypeMapper.insert(ductType);
        // 返回
        return ductType.getId();
    }

    @Override
    public void updateductType(ProductTypeUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductTypeExists(updateReqVO.getId());
        // 更新
        ProductTypeDO updateObj = ProductTypeConvert.INSTANCE.convert(updateReqVO);
        ductTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteductType(Long id) {
        // 校验存在
        this.validateductTypeExists(id);
        // 删除
        ductTypeMapper.deleteById(id);
    }

    private void validateductTypeExists(Long id) {
        if (ductTypeMapper.selectById(id) == null) {
            throw exception(PRODUCT_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public ProductTypeDO getductType(Long id) {
        return ductTypeMapper.selectById(id);
    }

    @Override
    public List<ProductTypeDO> getductTypeList(Collection<Long> ids) {
        return ductTypeMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductTypeDO> getductTypePage(ProductTypePageReqVO pageReqVO) {
        return ductTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductTypeDO> getductTypeList(ProductTypeExportReqVO exportReqVO) {
        return ductTypeMapper.selectList(exportReqVO);
    }

}
