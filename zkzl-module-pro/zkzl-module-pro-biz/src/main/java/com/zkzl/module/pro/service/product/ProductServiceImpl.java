package com.zkzl.module.pro.service.product;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.module.pro.controller.admin.product.vo.*;
import com.zkzl.module.pro.convert.product.ProductConvert;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import com.zkzl.module.pro.dal.dataobject.productcertificate.ProductCertificateDO;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import com.zkzl.module.pro.dal.mysql.product.ProductMapper;
import com.zkzl.module.pro.dal.mysql.productcertificate.ProductCertificateMapper;
import com.zkzl.module.pro.dal.mysql.productparameters.ProductParametersMapper;
import com.zkzl.module.pro.dal.mysql.productpic.ProductPicMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private ProductParametersMapper productParametersMapper;
    @Resource
    private ProductPicMapper productPicMapper;
    @Resource
    private ProductCertificateMapper productCertificateMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createduct(ProductCreateReqVO createReqVO) {
        // 产品id
        String productId = IdUtil.getSnowflakeNextIdStr();
        createReqVO.setProductId(productId);
        // 插入
        //插入产品参数
        addProductParameters(createReqVO.getProductParametersDOS(), productId, 0L);
        //插入产品图片
        if (createReqVO.getPicDOS() != null) {
            List<ProductPicDO> productPicDOList = createReqVO.getPicDOS().stream()
                    .map(pic -> pic.setProductId(productId)).collect(Collectors.toList());
            productPicMapper.insertBatch(productPicDOList);
        }
        //插入产品证书
        if (createReqVO.getProductCertificateDOS() != null) {
            List<ProductCertificateDO> productCertificateDOList = createReqVO.getProductCertificateDOS().stream()
                    .map(certificate -> certificate.setProductId(productId)).collect(Collectors.toList());
            productCertificateMapper.insertBatch(productCertificateDOList);
        }
        ProductDO duct = ProductConvert.INSTANCE.convert(createReqVO);
        ductMapper.insert(duct);
        // 返回
        return duct.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateduct(ProductUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductExists(updateReqVO.getProductId());
        //删除以前的关联数据
        productParametersMapper.delete(new QueryWrapper<ProductParametersDO>().lambda().eq(ProductParametersDO::getProductId,updateReqVO.getProductId()));
        productPicMapper.delete(new QueryWrapper<ProductPicDO>().lambda().eq(ProductPicDO::getProductId,updateReqVO.getProductId()));
        productCertificateMapper.delete(new QueryWrapper<ProductCertificateDO>().lambda().eq(ProductCertificateDO::getProductId,updateReqVO.getProductId()));
        // 更新

        //插入产品参数
        addProductParameters(updateReqVO.getProductParametersDOS(),updateReqVO.getProductId(),0L);
        //插入产品图片
        if (updateReqVO.getPicDOS() != null) {
            List<ProductPicDO> productPicDOList = updateReqVO.getPicDOS().stream()
                    .map(pic -> pic.setProductId(updateReqVO.getProductId())).collect(Collectors.toList());
            productPicMapper.insertBatch(productPicDOList);
        }
        //插入产品证书
        if (updateReqVO.getProductCertificateDOS() != null) {
            List<ProductCertificateDO> productCertificateDOList = updateReqVO.getProductCertificateDOS().stream()
                    .map(certificate -> certificate.setProductId(updateReqVO.getProductId())).collect(Collectors.toList());
            productCertificateMapper.insertBatch(productCertificateDOList);
        }
        ProductDO updateObj = ProductConvert.INSTANCE.convert(updateReqVO);
        ductMapper.update(updateObj,new UpdateWrapper<ProductDO>().lambda().eq(ProductDO::getProductId,updateReqVO.getProductId()));
    }

    @Override
    public void deleteduct(String id) {
        // 校验存在
        this.validateductExists(id);
        // 删除
        productParametersMapper.delete(new QueryWrapper<ProductParametersDO>().lambda().eq(ProductParametersDO::getProductId,id));
        productPicMapper.delete(new QueryWrapper<ProductPicDO>().lambda().eq(ProductPicDO::getProductId,id));
        productCertificateMapper.delete(new QueryWrapper<ProductCertificateDO>().lambda().eq(ProductCertificateDO::getProductId,id));
        ductMapper.delete(new QueryWrapper<ProductDO>().lambda().eq(ProductDO::getProductId, id));
    }

    private void validateductExists(String id) {
        ProductDO productDO = ductMapper.selectOne(new QueryWrapper<ProductDO>().lambda().eq(ProductDO::getProductId, id));
        if (productDO == null) {
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

    @Override
    public ProductRespVO getProduct(String productId) {
        ProductRespVO product = ductMapper.getProduct(productId);
        product.setProductParametersDOS(streamMethod(0L, product.getProductParametersDOS()));
        return product;
    }

    @Override
    public void updateShipped(String productId) {
        ProductDO productDO = ductMapper.selectOne(new QueryWrapper<ProductDO>().lambda().eq(ProductDO::getProductId, productId));
        if (productDO == null) {
            throw exception(PRODUCT_NOT_EXISTS);
        }
        if ("1".equals(productDO.getShipped())) {
            productDO.setShipped("0");
        } else {
            productDO.setShipped("1");
        }
        ductMapper.update(productDO, new UpdateWrapper<ProductDO>().lambda().eq(ProductDO::getProductId, productId));
    }

    private void addProductParameters(List<ProductParametersDO> parametersVOList, String productId, Long parentId) {
        for (ProductParametersDO parametersDO : parametersVOList) {
            parametersDO.setProductId(productId);
            parametersDO.setParentParamId(parentId);
            productParametersMapper.insert(parametersDO);
            if (parametersDO.getParametersDOS() != null) {
                addProductParameters(parametersDO.getParametersDOS(), productId, parametersDO.getId());
            }
        }
    }


    /**
     * stream 方法转换树形结构方法的优化
     *
     * @param parentId
     * @param treeList
     * @return
     */
    public static List<ProductParametersDO> streamMethod(Long parentId, List<ProductParametersDO> treeList) {
        List<ProductParametersDO> list = treeList.stream()
                // 筛选父节点
                .filter(t -> t.getParentParamId().equals(parentId))
                // 递归设置子节点
                .map(item -> {
                    item.setParametersDOS(streamMethod(item.getId(), treeList));
                    return item;
                })
                .collect(Collectors.toList());
        return list;
    }


}
