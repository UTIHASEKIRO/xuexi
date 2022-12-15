package com.zkzl.module.pro.controller.app.product;

import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.security.core.annotations.PreAuthenticated;
import com.zkzl.module.pro.controller.app.product.vo.ProductDescVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductReqVO;
import com.zkzl.module.pro.controller.app.product.vo.ProductVO;
import com.zkzl.module.pro.dal.dataobject.producttype.ProductTypeDO;
import com.zkzl.module.pro.service.product.ProductService;
import com.zkzl.module.pro.service.producttype.ProductTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "app - 询价")
@RestController
@RequestMapping("/pro/app-product")
@Validated
public class AppProductController {

    @Resource
    private ProductService productService;

    @Resource
    private ProductTypeService ductTypeService;

    @GetMapping("/page")
    @ApiOperation("获得产品分页")
    @PermitAll
    public CommonResult<PageResult<ProductDescVO>> pageApp(ProductReqVO productReqVO) {
        PageResult<ProductDescVO> pageResult = productService.pageApp(productReqVO);
        return success(pageResult);
    }

    @GetMapping("/getDesc")
    @ApiOperation("获得产品详情")
    @PermitAll
    public CommonResult<ProductDescVO> getDesc(@RequestParam(value = "id")  Long id) {
        ProductDescVO result = productService.getDesc(id);
        return success(result);
    }

    @GetMapping("/recommend")
    @ApiOperation("产品推荐")
    public CommonResult<PageResult<ProductDescVO>> pageApp(String id) {
        PageResult<ProductDescVO> pageResult = productService.recommend(Long.parseLong(id));
        return success(pageResult);
    }

    @GetMapping("/product-list")
    @ApiOperation("获得商品类别列表")
    @PermitAll
    public CommonResult<List<ProductTypeDO>> getHeaderDuctTypeList(@RequestParam(value = "typeName", required = false) String typeName) {
        return success(ductTypeService.getHeaderDuctTypeList(typeName));
    }
}
