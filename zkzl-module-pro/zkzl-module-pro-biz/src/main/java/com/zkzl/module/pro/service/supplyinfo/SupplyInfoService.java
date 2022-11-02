package com.zkzl.module.pro.service.supplyinfo;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.supplyinfo.vo.*;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;
import com.zkzl.module.pro.dal.dataobject.supplyinfo.SupplyInfoDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 供货商信息 Service 接口
 *
 * @author 芋道源码
 */
public interface SupplyInfoService {

    /**
     * 创建供货商信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSupplyInfo(@Valid SupplyInfoCreateReqVO createReqVO);

    /**
     * 更新供货商信息
     *
     * @param updateReqVO 更新信息
     */
    void updateSupplyInfo(@Valid SupplyInfoUpdateReqVO updateReqVO);

    /**
     * 删除供货商信息
     *
     * @param id 编号
     */
    void deleteSupplyInfo(Long id);

    /**
     * 获得供货商信息
     *
     * @param id 编号
     * @return 供货商信息
     */
    SupplyInfoDO getSupplyInfo(Long id);

    /**
     * 获得供货商信息列表
     *
     * @param ids 编号
     * @return 供货商信息列表
     */
    List<SupplyInfoDO> getSupplyInfoList(Collection<Long> ids);

    /**
     * 获得供货商信息分页
     *
     * @param pageReqVO 分页查询
     * @return 供货商信息分页
     */
    PageResult<SupplyInfoPageVO> getSupplyInfoPage(SupplyInfoPageReqVO pageReqVO);

    /**
     * 获得供货商信息列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 供货商信息列表
     */
    List<SupplyInfoDO> getSupplyInfoList(SupplyInfoExportReqVO exportReqVO);

    /*获得供货商信息-产品类别*/
    List<ProductTypeDO> getProductType();
}
