package com.zkzl.module.pro.service.product;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.product.vo.*;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.product.ProductConvert;
import com.zkzl.module.pro.dal.mysql.product.ProductMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRODUCT_NOT_EXISTS;

/**
 * 产品 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper ductMapper;

    @Override
    public Long createduct(ProductCreateReqVO createReqVO) {
        // 插入
        ProductDO duct = ProductConvert.INSTANCE.convert(createReqVO);
        ductMapper.insert(duct);
        // 返回
        return duct.getId();
    }

    @Override
    public void updateduct(ProductUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductExists(updateReqVO.getId());
        // 更新
        ProductDO updateObj = ProductConvert.INSTANCE.convert(updateReqVO);
        ductMapper.updateById(updateObj);
    }

    @Override
    public void deleteduct(Long id) {
        // 校验存在
        this.validateductExists(id);
        // 删除
        ductMapper.deleteById(id);
    }

    private void validateductExists(Long id) {
        if (ductMapper.selectById(id) == null) {
            throw exception(PRODUCT_NOT_EXISTS);
        }
    }

    @Override
    public ProductDO getduct(Long id) {
        return ductMapper.selectById(id);
    }

    @Override
    public List<ProductDO> getductList(Collection<Long> ids) {
        return ductMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductDO> getductPage(ProductPageReqVO pageReqVO) {
        return ductMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductDO> getductList(ProductExportReqVO exportReqVO) {
        return ductMapper.selectList(exportReqVO);
    }

}
