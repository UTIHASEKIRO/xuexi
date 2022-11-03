package com.zkzl.module.pro.service.priceinqury;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.priceinqury.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqury.PriceInquryDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 询价 Service 接口
 *
 * @author 芋道源码
 */
public interface PriceInquryService {

    /**
     * 创建询价
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPriceInqury(@Valid PriceInquryCreateReqVO createReqVO);

    /**
     * 更新询价
     *
     * @param updateReqVO 更新信息
     */
    void updatePriceInqury(@Valid PriceInquryUpdateReqVO updateReqVO);

    /**
     * 删除询价
     *
     * @param id 编号
     */
    void deletePriceInqury(Long id);

    /**
     * 获得询价
     *
     * @param id 编号
     * @return 询价
     */
    PriceInquryDO getPriceInqury(Long id);

    /**
     * 获得询价列表
     *
     * @param ids 编号
     * @return 询价列表
     */
    List<PriceInquryDO> getPriceInquryList(Collection<Long> ids);

    /**
     * 获得询价分页
     *
     * @param pageReqVO 分页查询
     * @return 询价分页
     */
    PageResult<PriceInquryDO> getPriceInquryPage(PriceInquryPageReqVO pageReqVO);

    /**
     * 获得询价列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 询价列表
     */
    List<PriceInquryDO> getPriceInquryList(PriceInquryExportReqVO exportReqVO);

    /*管理员查看询价表*/
    PageResult<PriceInquryPageVO> pageManage(PriceInquryPageReqVO pageVO);

    /*业务源查看询价表*/
    PageResult<PriceInquryPageVO> pageCommon(PriceInquryPageReqVO pageVO);
}
