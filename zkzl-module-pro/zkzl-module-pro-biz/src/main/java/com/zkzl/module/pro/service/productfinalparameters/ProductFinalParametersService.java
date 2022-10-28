package com.zkzl.module.pro.service.productfinalparameters;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.productfinalparameters.vo.*;
import com.zkzl.module.pro.dal.dataobject.productfinalparameters.ProductFinalParametersDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 产品固定参数 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductFinalParametersService {

    /**
     * 创建产品固定参数
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createductFinalParameters(@Valid ProductFinalParametersCreateReqVO createReqVO);

    /**
     * 更新产品固定参数
     *
     * @param updateReqVO 更新信息
     */
    void updateductFinalParameters(@Valid ProductFinalParametersUpdateReqVO updateReqVO);

    /**
     * 删除产品固定参数
     *
     * @param id 编号
     */
    void deleteductFinalParameters(Long id);

    /**
     * 获得产品固定参数
     *
     * @param id 编号
     * @return 产品固定参数
     */
    ProductFinalParametersDO getductFinalParameters(Long id);

    /**
     * 获得产品固定参数列表
     *
     * @param ids 编号
     * @return 产品固定参数列表
     */
    List<ProductFinalParametersDO> getductFinalParametersList(Collection<Long> ids);

    /**
     * 获得产品固定参数分页
     *
     * @param pageReqVO 分页查询
     * @return 产品固定参数分页
     */
    PageResult<ProductFinalParametersDO> getductFinalParametersPage(ProductFinalParametersPageReqVO pageReqVO);

    /**
     * 获得产品固定参数列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品固定参数列表
     */
    List<ProductFinalParametersDO> getductFinalParametersList(ProductFinalParametersExportReqVO exportReqVO);

}
