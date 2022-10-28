package com.zkzl.module.pro.service.productparameters;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.productparameters.vo.*;
import com.zkzl.module.pro.dal.dataobject.productparameters.ProductParametersDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 产品参数 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductParametersService {

    /**
     * 创建产品参数
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createductParameters(@Valid ProductParametersCreateReqVO createReqVO);

    /**
     * 更新产品参数
     *
     * @param updateReqVO 更新信息
     */
    void updateductParameters(@Valid ProductParametersUpdateReqVO updateReqVO);

    /**
     * 删除产品参数
     *
     * @param id 编号
     */
    void deleteductParameters(Long id);

    /**
     * 获得产品参数
     *
     * @param id 编号
     * @return 产品参数
     */
    ProductParametersDO getductParameters(Long id);

    /**
     * 获得产品参数列表
     *
     * @param ids 编号
     * @return 产品参数列表
     */
    List<ProductParametersDO> getductParametersList(Collection<Long> ids);

    /**
     * 获得产品参数分页
     *
     * @param pageReqVO 分页查询
     * @return 产品参数分页
     */
    PageResult<ProductParametersDO> getductParametersPage(ProductParametersPageReqVO pageReqVO);

    /**
     * 获得产品参数列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品参数列表
     */
    List<ProductParametersDO> getductParametersList(ProductParametersExportReqVO exportReqVO);

}
