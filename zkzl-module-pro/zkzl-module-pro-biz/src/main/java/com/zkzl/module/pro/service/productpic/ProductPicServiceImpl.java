package com.zkzl.module.pro.service.productpic;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.zkzl.module.pro.controller.admin.productpic.vo.*;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.pro.convert.productpic.ProductPicConvert;
import com.zkzl.module.pro.dal.mysql.productpic.ProductPicMapper;

import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.system.enums.ErrorCodeConstants.PRODUCT_PIC_NOT_EXISTS;

/**
 * 产品图片 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProductPicServiceImpl implements ProductPicService {

    @Resource
    private ProductPicMapper ductPicMapper;

    @Override
    public Long createductPic(ProductPicCreateReqVO createReqVO) {
        // 插入
        ProductPicDO ductPic = ProductPicConvert.INSTANCE.convert(createReqVO);
        ductPicMapper.insert(ductPic);
        // 返回
        return ductPic.getId();
    }

    @Override
    public void updateductPic(ProductPicUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductPicExists(updateReqVO.getId());
        // 更新
        ProductPicDO updateObj = ProductPicConvert.INSTANCE.convert(updateReqVO);
        ductPicMapper.updateById(updateObj);
    }

    @Override
    public void deleteductPic(Long id) {
        // 校验存在
        this.validateductPicExists(id);
        // 删除
        ductPicMapper.deleteById(id);
    }

    private void validateductPicExists(Long id) {
        if (ductPicMapper.selectById(id) == null) {
            throw exception(PRODUCT_PIC_NOT_EXISTS);
        }
    }

    @Override
    public ProductPicDO getductPic(Long id) {
        return ductPicMapper.selectById(id);
    }

    @Override
    public List<ProductPicDO> getductPicList(Collection<Long> ids) {
        return ductPicMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ProductPicDO> getductPicPage(ProductPicPageReqVO pageReqVO) {
        return ductPicMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductPicDO> getductPicList(ProductPicExportReqVO exportReqVO) {
        return ductPicMapper.selectList(exportReqVO);
    }

}
