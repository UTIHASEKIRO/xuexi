package com.zkzl.module.pro.service.priceinqurychild;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqurychild.PriceInquryChildDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 询价表子 Service 接口
 *
 * @author 芋道源码
 */
public interface PriceInquryChildService {

    /**
     * 创建询价表子
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPriceInquryChild(@Valid PriceInquryChildCreateReqVO createReqVO);

    /**
     * 更新询价表子
     *
     * @param updateReqVO 更新信息
     */
    void updatePriceInquryChild(@Valid PriceInquryChildUpdateReqVO updateReqVO);

    /**
     * 删除询价表子
     *
     * @param id 编号
     */
    void deletePriceInquryChild(Long id);

    /**
     * 获得询价表子
     *
     * @param id 编号
     * @return 询价表子
     */
    PriceInquryChildDO getPriceInquryChild(Long id);

    /**
     * 获得询价表子列表
     *
     * @param ids 编号
     * @return 询价表子列表
     */
    List<PriceInquryChildDO> getPriceInquryChildList(Collection<Long> ids);

    /**
     * 获得询价表子分页
     *
     * @param pageReqVO 分页查询
     * @return 询价表子分页
     */
    PageResult<PriceInquryChildDO> getPriceInquryChildPage(PriceInquryChildPageReqVO pageReqVO);

    /**
     * 获得询价表子列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 询价表子列表
     */
    List<PriceInquryChildDO> getPriceInquryChildList(PriceInquryChildExportReqVO exportReqVO);

}
