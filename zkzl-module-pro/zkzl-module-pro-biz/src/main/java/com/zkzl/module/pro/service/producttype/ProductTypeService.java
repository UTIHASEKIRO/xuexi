package com.zkzl.module.pro.service.producttype;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.producttype.vo.*;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 商品类别 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductTypeService {

    /**
     * 创建商品类别
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    String createductType(@Valid ProductTypeCreateReqVO createReqVO);

    /**
     * 更新商品类别
     *
     * @param updateReqVO 更新信息
     */
    void updateductType(@Valid ProductTypeUpdateReqVO updateReqVO);

    /**
     * 删除商品类别
     *
     * @param id 编号
     */
    void deleteductType(String id);

    /**
     * 获得商品类别
     *
     * @param typeId 编号
     * @return 商品类别
     */
    ProductTypeDO getductType(String typeId);

    /**
     * 获得商品类别列表
     *
     * @return 商品类别列表
     */
    List<ProductTypeDO> getductTypeList(String typeName);

    /**
     * 获得商品类别分页
     *
     * @param pageReqVO 分页查询
     * @return 商品类别分页
     */
    PageResult<ProductTypeDO> getductTypePage(ProductTypePageReqVO pageReqVO);

    /**
     * 获得商品类别列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 商品类别列表
     */
    List<ProductTypeDO> getductTypeList(ProductTypeExportReqVO exportReqVO);

    List<ProductTypeDO> getHeaderDuctTypeList(String typeName);
}
