package com.zkzl.module.pro.controller.app.priceinqury;

import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.framework.common.pojo.PageParam;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.security.core.annotations.PreAuthenticated;
import com.zkzl.module.pro.controller.admin.priceinqury.vo.*;
import com.zkzl.module.pro.controller.app.priceinqury.vo.PriceInquryHistoryVO;
import com.zkzl.module.pro.service.priceinqury.PriceInquryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "app - 询价")
@RestController
@RequestMapping("/pro/app-price-inqury")
@Validated
public class AppPriceInquryController {

    @Resource
    private PriceInquryService priceInquryService;

    @PostMapping("/add")
    @ApiOperation("用户端产品添加询价信息")
    @PreAuthenticated
    public CommonResult<Boolean> addPriceInqury(@Valid @RequestBody PriceInquryCreateReqVO param) {
        priceInquryService.addPriceInqury(param);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("用户端获得询价")
    @PreAuthenticated
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<PriceInquryAndChildsVO> getPriceInqury(@RequestParam("id") Long id) {
        PriceInquryAndChildsVO priceInqury = priceInquryService.getPriceInquryAndChilds(id);
        return success(priceInqury);
    }

    @GetMapping("/inquryHistory")
    @ApiOperation("用户询价历史")
    @PreAuthenticated
    public CommonResult<PageResult<PriceInquryHistoryVO>> inquryHistory(PageParam param) {
        PageResult<PriceInquryHistoryVO> results = priceInquryService.inquryHistory(param);
        return success(results);
    }

    @PutMapping("/update")
    @ApiOperation("更新询价")
    @PreAuthenticated
    public CommonResult<Boolean> updatePriceInqury(@Valid @RequestBody PriceInquryUpdateReqVO updateReqVO) {
        priceInquryService.updatePriceInqury(updateReqVO);
        return success(true);
    }

}
