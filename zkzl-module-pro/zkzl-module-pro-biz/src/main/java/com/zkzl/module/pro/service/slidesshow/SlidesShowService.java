package com.zkzl.module.pro.service.slidesshow;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.slidesshow.vo.*;
import com.zkzl.module.pro.dal.dataobject.slidesshow.SlidesShowDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 轮播图 Service 接口
 *
 * @author 芋道源码
 */
public interface SlidesShowService {

    /**
     * 创建轮播图
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSlidesShow(@Valid SlidesShowCreateReqVO createReqVO);

    /**
     * 更新轮播图
     *
     * @param updateReqVO 更新信息
     */
    void updateSlidesShow(@Valid SlidesShowUpdateReqVO updateReqVO);

    /**
     * 删除轮播图
     *
     * @param id 编号
     */
    void deleteSlidesShow(Long id);

    /**
     * 获得轮播图
     *
     * @param id 编号
     * @return 轮播图
     */
    SlidesShowDO getSlidesShow(Long id);

    /**
     * 获得轮播图列表
     *
     * @param ids 编号
     * @return 轮播图列表
     */
    List<SlidesShowDO> getSlidesShowList(Collection<Long> ids);

    /**
     * 获得轮播图分页
     *
     * @param pageReqVO 分页查询
     * @return 轮播图分页
     */
    PageResult<SlidesShowDO> getSlidesShowPage(SlidesShowPageReqVO pageReqVO);

    /**
     * 获得轮播图列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 轮播图列表
     */
    List<SlidesShowDO> getSlidesShowList(SlidesShowExportReqVO exportReqVO);

}
