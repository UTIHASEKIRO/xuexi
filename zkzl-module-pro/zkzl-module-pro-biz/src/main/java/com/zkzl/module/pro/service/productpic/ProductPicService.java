package com.zkzl.module.pro.service.productpic;

import java.util.*;
import javax.validation.*;
import com.zkzl.module.pro.controller.admin.productpic.vo.*;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import com.zkzl.framework.common.pojo.PageResult;

/**
 * 产品图片 Service 接口
 *
 * @author 芋道源码
 */
public interface ProductPicService {

    /**
     * 创建产品图片
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createductPic(@Valid ProductPicCreateReqVO createReqVO);

    /**
     * 更新产品图片
     *
     * @param updateReqVO 更新信息
     */
    void updateductPic(@Valid ProductPicUpdateReqVO updateReqVO);

    /**
     * 删除产品图片
     *
     * @param id 编号
     */
    void deleteductPic(Long id);

    /**
     * 获得产品图片
     *
     * @param id 编号
     * @return 产品图片
     */
    ProductPicDO getductPic(Long id);

    /**
     * 获得产品图片列表
     *
     * @param ids 编号
     * @return 产品图片列表
     */
    List<ProductPicDO> getductPicList(Collection<Long> ids);

    /**
     * 获得产品图片分页
     *
     * @param pageReqVO 分页查询
     * @return 产品图片分页
     */
    PageResult<ProductPicDO> getductPicPage(ProductPicPageReqVO pageReqVO);

    /**
     * 获得产品图片列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品图片列表
     */
    List<ProductPicDO> getductPicList(ProductPicExportReqVO exportReqVO);

}
