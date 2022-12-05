package com.zkzl.module.pro.service.product;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.module.pro.controller.admin.product.vo.*;
import com.zkzl.module.pro.controller.app.product.vo.ProductDescVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductReqVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductVO;
import com.zkzl.module.pro.dal.dataobject.product.ProductDO;

import javax.validation.Valid;
import java.util.List;

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
     * @param typeId 类别Id
     * @return 产品列表
     */
    List<ProductDO> getductList(String typeId);

    /**
     * 获得产品分页
     *
     * @param pageReqVO 分页查询
     * @return 产品分页
     */
    PageResult<ProductDO> getductPage(ProductPageReqVO pageReqVO);

    /**
     * 获得产品列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 产品列表
     */
    List<ProductDO> getductList(ProductExportReqVO exportReqVO);

    ProductRespVO getProduct(String productId);

    void updateShipped(String productId);

    /*
    * 用户端 获取产品列表
    * */
    PageResult<ProductVO> pageApp(ProductReqVO productReqVO);

    /*
    * 用户端查看产品详情
    * */
    ProductDescVO getDesc(Long id);

    /*根据产品id 查询相关产品*/
    PageResult<ProductVO> recommend(Long id);
}
