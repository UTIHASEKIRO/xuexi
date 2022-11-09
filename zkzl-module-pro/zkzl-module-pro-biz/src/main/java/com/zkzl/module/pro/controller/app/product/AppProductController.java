package com.zkzl.module.pro.controller.app.product;

import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.framework.common.pojo.PageParam;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.module.pro.controller.app.product.vo.ProductVO;
import com.zkzl.module.pro.service.priceinqury.PriceInquryService;
import com.zkzl.module.pro.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "app - 询价")
@RestController
@RequestMapping("/pro/app-product")
@Validated
public class AppProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/page")
    @ApiOperation("获得产品分页")
    public CommonResult<PageResult<ProductVO>> pageApp(PageParam param) {
        PageResult<ProductVO> pageResult = productService.pageApp(param);
        return success(pageResult);
    }
}
