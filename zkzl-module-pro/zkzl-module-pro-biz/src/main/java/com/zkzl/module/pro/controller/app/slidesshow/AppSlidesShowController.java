package com.zkzl.module.pro.controller.app.slidesshow;

import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.module.pro.controller.admin.slidesshow.vo.SlidesShowRespVO;
import com.zkzl.module.pro.convert.slidesshow.SlidesShowConvert;
import com.zkzl.module.pro.dal.dataobject.slidesshow.SlidesShowDO;
import com.zkzl.module.pro.service.slidesshow.SlidesShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.List;

import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "客户端 - 轮播图")
@RestController
@RequestMapping("/pro/slides")
@Validated
public class AppSlidesShowController {

    @Resource
    private SlidesShowService slidesShowService;

    @GetMapping("/list")
    @ApiOperation("获得轮播图列表")
    @PermitAll
    public CommonResult<List<SlidesShowRespVO>> getSlidesShowList() {
        List<SlidesShowDO> list = slidesShowService.getSlidesList();
        return success(SlidesShowConvert.INSTANCE.convertList(list));
    }
}
