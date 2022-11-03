package com.zkzl.module.pro.service.product;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.util.MyBatisUtils;
import com.zkzl.module.pro.controller.admin.product.vo.*;
import com.zkzl.module.pro.convert.product.ProductConvert;
import com.zkzl.module.pro.convert.productparameters.ProductParametersConvert;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import com.zkzl.module.pro.dal.mysql.product.ProductMapper;
import com.zkzl.module.pro.dal.mysql.productparameters.ProductParametersMapper;
import com.zkzl.module.pro.dal.mysql.productpic.ProductPicMapper;
import org.springframework.stereotype.Service;
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

    @Override
    public Long createduct(ProductCreateReqVO createReqVO) {
        // 产品id
        String productId = IdUtil.getSnowflakeNextIdStr();
        createReqVO.setProductId(productId);
        // 插入
        ProductDO duct = ProductConvert.INSTANCE.convert(createReqVO);
        ductMapper.insert(duct);

        //插入产品参数
        addProductParameters(createReqVO.getParametersVOList(),productId,0L);
        //插入产品图片
        if(createReqVO.getPicDOS()!=null){
            for (ProductPicDO productPicDO: createReqVO.getPicDOS()) {
                productPicDO.setProductId(productId);
                productPicMapper.insert(productPicDO);
            }
        }
        // 返回
        return duct.getId();
    }

    @Override
    public void updateduct(ProductUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductExists(updateReqVO.getProductId());
        //删除以前的关联数据
        productParametersMapper.delete(new QueryWrapper<ProductParametersDO>().lambda().eq(ProductParametersDO::getProductId,updateReqVO.getProductId()));
        productPicMapper.delete(new QueryWrapper<ProductPicDO>().lambda().eq(ProductPicDO::getProductId,updateReqVO.getProductId()));
        // 更新

        //插入产品参数
        addProductParameters(updateReqVO.getParametersVOList(),updateReqVO.getProductId(),0L);
        //插入产品图片
        if(updateReqVO.getPicDOS()!=null){
            for (ProductPicDO productPicDO: updateReqVO.getPicDOS()) {
                productPicDO.setProductId(updateReqVO.getProductId());
                productPicMapper.insert(productPicDO);
            }
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
    public PageResult<ProductRespVO> getductPage(ProductPageReqVO pageReqVO) {
        Page<ProductRespVO> page = MyBatisUtils.buildPage(pageReqVO);
        Page<ProductRespVO> productPage = ductMapper.getProductPage(page, pageReqVO);
        for (ProductRespVO productRespVO : productPage.getRecords()) {
            for (ProductParametersDO productParametersDO : productRespVO.getParametersDOS()) {
                switch (productParametersDO.getParameterCn()) {
                    case "HS编号":
                        productRespVO.setHsNO(productParametersDO.getValueCn());
                        break;
                    case "产品型号":
                        productRespVO.setProductModel(productParametersDO.getValueCn());
                        break;
                    case "用途分类":
                        productRespVO.setUseClassification(productParametersDO.getValueCn());
                        break;
                    case "颜色":
                        productRespVO.setColour(productParametersDO.getValueCn());
                        break;
                    case "包装方式":
                        productRespVO.setPackagingMethod(productParametersDO.getValueCn());
                        break;
                    case "交货方式":
                        productRespVO.setDeliveryMethod(productParametersDO.getValueCn());
                        break;
                    default:
                        break;
                }
            }
        }
        return new PageResult<>(productPage.getRecords(), productPage.getTotal());
    }

    @Override
    public List<ProductDO> getductList(ProductExportReqVO exportReqVO) {
        return ductMapper.selectList(exportReqVO);
    }

    @Override
    public ProductRespVO getProduct(String productId) {
        ProductRespVO product = ductMapper.getProduct(productId);
        product.setParametersDOS(streamMethod(0L,product.getParametersDOS()));
        return product;
    }

    private void addProductParameters(List<ParametersVO> parametersVOList, String productId, Long parentId) {
        for (ParametersVO parametersVO : parametersVOList) {
            ProductParametersDO parametersDO = ProductParametersConvert.INSTANCE.convert(parametersVO);
            parametersDO.setProductId(productId);
            parametersDO.setParentParamId(parentId);
            productParametersMapper.insert(parametersDO);
            if (parametersVO.getParametersVOS() != null) {
                addProductParameters(parametersVO.getParametersVOS(), productId, parametersDO.getId());
            }
        }
    }


    /**
     * stream 方法转换树形结构方法的优化
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
