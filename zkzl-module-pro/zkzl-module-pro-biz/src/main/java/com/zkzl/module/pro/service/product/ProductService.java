package com.zkzl.module.pro.service.product;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.product.vo.*;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 产品 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductService {

    /**
     * 创建产品
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createduct(@Valid ProductCreateReqVO createReqVO);

    /**
     * 更新产品
     *
     * @param updateReqVO 更新信息
     */
    void updateduct(@Valid ProductUpdateReqVO updateReqVO);

    /**
     * 删除产品
     *
     * @param id 编号
     */
    void deleteduct(String id);

    /**
     * 获得产品
     *
     * @param id 编号
     * @return 产品
     */
    ProductDO getduct(Long id);

    /**
     * 获得产品列表
     *
     * @param ids 编号
     * @return 产品列表
     */
    List<ProductDO> getductList(Collection<Long> ids);

    /**
     * 获得产品分页
     *
     * @param pageReqVO 分页查询
     * @return 产品分页
     */
    PageResult<ProductRespVO> getductPage(ProductPageReqVO pageReqVO);

    /**
     * 获得产品列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品列表
     */
    List<ProductDO> getductList(ProductExportReqVO exportReqVO);

    ProductRespVO getProduct(String productId);
}
