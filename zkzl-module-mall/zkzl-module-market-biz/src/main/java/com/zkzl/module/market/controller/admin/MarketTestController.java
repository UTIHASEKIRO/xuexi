package com.zkzl.module.market.controller.admin;

import com.zkzl.framework.common.pojo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "管理后台 - 营销")
@RestController
@RequestMapping("/market/test")
@Validated
public class MarketTestController {

    @GetMapping("/get")
    @ApiOperation("获取 market 信息")
    public CommonResult<String> get() {
        return success("true");
    }

}
