package com.zkzl.module.pro.service.producttype;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

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
    public String createductType(ProductTypeCreateReqVO createReqVO) {
        createReqVO.setTypeId(IdUtil.getSnowflakeNextIdStr());
        if(createReqVO.getParentId() == null){
            createReqVO.setParentId("0");
        }
        // 插入
        ProductTypeDO ductType = ProductTypeConvert.INSTANCE.convert(createReqVO);
        ductTypeMapper.insert(ductType);
        // 返回
        return ductType.getTypeId();
    }

    @Override
    public void updateductType(ProductTypeUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateductTypeExists(updateReqVO.getTypeId());
        // 更新
        ProductTypeDO updateObj = ProductTypeConvert.INSTANCE.convert(updateReqVO);
        ductTypeMapper.update(updateObj,new UpdateWrapper<ProductTypeDO>().lambda().eq(ProductTypeDO::getTypeId,updateReqVO.getTypeId()));
    }

    @Override
    public void deleteductType(String id) {
        // 校验存在
        this.validateductTypeExists(id);
        // 删除
        ductTypeMapper.delete(new QueryWrapper<ProductTypeDO>().lambda().eq(ProductTypeDO::getTypeId, id));
    }

    private void validateductTypeExists(String id) {
        ProductTypeDO productTypeDO = ductTypeMapper.selectOne(new QueryWrapper<ProductTypeDO>().lambda().eq(ProductTypeDO::getTypeId, id));
        if (productTypeDO == null) {
            throw exception(PRODUCT_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public ProductTypeDO getductType(String typeId) {
        return ductTypeMapper.selectOne(new QueryWrapper<ProductTypeDO>().lambda().eq(ProductTypeDO::getTypeId, typeId));
    }

    @Override
    public List<ProductTypeDO> getductTypeList(String typeName) {
        List<ProductTypeDO> productTypeDOS = ductTypeMapper.selectList(new LambdaQueryWrapperX<ProductTypeDO>()
                .likeIfPresent(ProductTypeDO::getTypeNameCn,typeName)
                .orX()
                .likeIfPresent(ProductTypeDO::getTypeNameEn,typeName)
                .orderByAsc(ProductTypeDO::getSort)
        );
        return productTypeDOS;
        //return streamMethod("0",productTypeDOS);
    }

    @Override
    public List<ProductTypeDO> getHeaderDuctTypeList(String typeName) {
        List<ProductTypeDO> productTypeDOS = ductTypeMapper.selectList(new LambdaQueryWrapperX<ProductTypeDO>()
                .likeIfPresent(ProductTypeDO::getTypeNameCn,typeName)
                .orX()
                .likeIfPresent(ProductTypeDO::getTypeNameEn,typeName)
                .orderByAsc(ProductTypeDO::getSort)
                .last("limit 16")
        );
        return productTypeDOS;
        //return streamMethod("0",productTypeDOS);
    }
    @Override
    public PageResult<ProductTypeDO> getductTypePage(ProductTypePageReqVO pageReqVO) {
        return ductTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ProductTypeDO> getductTypeList(ProductTypeExportReqVO exportReqVO) {
        return ductTypeMapper.selectList(exportReqVO);
    }
    /**
     * stream 方法转换树形结构方法的优化
     * @param parentId
     * @param treeList
     * @return
     */
    public static List<ProductTypeDO> streamMethod(String parentId, List<ProductTypeDO> treeList) {
        List<ProductTypeDO> list = treeList.stream()
                // 筛选父节点
                .filter(t -> t.getParentId().equals(parentId))
                // 递归设置子节点
                .map(item -> {
                    item.setProductTypeDOS(streamMethod(item.getTypeId(), treeList));
                    return item;
                })
                .collect(Collectors.toList());
        if(list.size() == 0) list = null;
        return list;
    }
}
