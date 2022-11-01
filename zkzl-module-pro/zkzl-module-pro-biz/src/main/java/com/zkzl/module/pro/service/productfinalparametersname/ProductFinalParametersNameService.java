package com.zkzl.module.pro.service.productfinalparametersname;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.productfinalparametersname.vo.*;
import com.zkzl.module.pro.dal.dataobject.productfinalparametersname.ProductFinalParametersNameDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 产品固定参数名 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductFinalParametersNameService {

    /**
     * 创建产品固定参数名
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createductFinalParametersName(@Valid ProductFinalParametersNameCreateReqVO createReqVO);

    /**
     * 更新产品固定参数名
     *
     * @param updateReqVO 更新信息
     */
    void updateductFinalParametersName(@Valid ProductFinalParametersNameUpdateReqVO updateReqVO);

    /**
     * 删除产品固定参数名
     *
     * @param id 编号
     */
    void deleteductFinalParametersName(Long id);

    /**
     * 获得产品固定参数名
     *
     * @param id 编号
     * @return 产品固定参数名
     */
    ProductFinalParametersNameDO getductFinalParametersName(Long id);

    /**
     * 获得产品固定参数名列表
     *
     * @param ids 编号
     * @return 产品固定参数名列表
     */
    List<ProductFinalParametersNameDO> getductFinalParametersNameList(Collection<Long> ids);

    /**
     * 获得产品固定参数名分页
     *
     * @param pageReqVO 分页查询
     * @return 产品固定参数名分页
     */
    PageResult<ProductFinalParametersNameDO> getductFinalParametersNamePage(ProductFinalParametersNamePageReqVO pageReqVO);

    /**
     * 获得产品固定参数名列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品固定参数名列表
     */
    List<ProductFinalParametersNameDO> getductFinalParametersNameList(ProductFinalParametersNameExportReqVO exportReqVO);

}
