package com.zkzl.module.product.service.brand;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.product.controller.admin.brand.vo.*;
import com.zkzl.module.product.dal.dataobject.brand.BrandDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.product.convert.brand.BrandConvert;
import com.zkzl.module.product.dal.mysql.brand.BrandMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.product.enums.ErrorCodeConstants.*;

/**
 * 品牌 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Override
    public Long createBrand(BrandCreateReqVO createReqVO) {
        // 插入
        BrandDO brand = BrandConvert.INSTANCE.convert(createReqVO);
        brandMapper.insert(brand);
        // 返回
        return brand.getId();
    }

    @Override
    public void updateBrand(BrandUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBrandExists(updateReqVO.getId());
        // 更新
        BrandDO updateObj = BrandConvert.INSTANCE.convert(updateReqVO);
        brandMapper.updateById(updateObj);
    }

    @Override
    public void deleteBrand(Long id) {
        // 校验存在
        this.validateBrandExists(id);
        // 删除
        brandMapper.deleteById(id);
    }

    private void validateBrandExists(Long id) {
        if (brandMapper.selectById(id) == null) {
            throw exception(BRAND_NOT_EXISTS);
        }
    }

    @Override
    public BrandDO getBrand(Long id) {
        return brandMapper.selectById(id);
    }

    @Override
    public List<BrandDO> getBrandList(Collection<Long> ids) {
        return brandMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BrandDO> getBrandPage(BrandPageReqVO pageReqVO) {
        return brandMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BrandDO> getBrandList(BrandExportReqVO exportReqVO) {
        return brandMapper.selectList(exportReqVO);
    }

}
