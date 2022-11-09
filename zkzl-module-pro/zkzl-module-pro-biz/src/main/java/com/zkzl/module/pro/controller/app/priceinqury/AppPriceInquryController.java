package com.zkzl.module.pro.controller.app.priceinqury;

import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.module.pro.controller.admin.priceinqury.vo.PriceInquryPageReqVO;
import com.zkzl.module.pro.controller.admin.priceinqury.vo.PriceInquryPageVO;
import com.zkzl.module.pro.service.priceinqury.PriceInquryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "app - 询价")
@RestController
@RequestMapping("/pro/price-inqury")
@Validated
public class AppPriceInquryController {

    @Resource
    private PriceInquryService priceInquryService;

    @GetMapping("/page")
    @ApiOperation("获得产品分页")
    public CommonResult<PageResult<PriceInquryPageVO>> pageCommon(@Valid PriceInquryPageReqVO pageVO) {
        PageResult<PriceInquryPageVO> pageResult = priceInquryService.pageCommon(pageVO);
        return success(pageResult);
    }
}
