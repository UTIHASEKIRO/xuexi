package com.zkzl.module.market.controller.app.banner;

import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.module.market.controller.admin.banner.vo.BannerRespVO;
import com.zkzl.module.market.convert.banner.BannerConvert;
import com.zkzl.module.market.dal.dataobject.banner.BannerDO;
import com.zkzl.module.market.service.banner.BannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.zkzl.framework.common.pojo.CommonResult.success;

/**
 * @author: XIA
 */
@RestController
@RequestMapping("/market/banner")
@Api(tags = "用户APP- 首页Banner")
@Validated
public class AppBannerController {

    // TODO @xia：使用 @Resource 哈
    @Autowired
    private BannerService bannerService;

    // TODO @xia：新建一个 AppBannerRespVO，只返回必要的字段。status 要过滤下。然后 sort 下结果
    @GetMapping("/list")
    @ApiOperation("获得banner列表")
    @PreAuthorize("@ss.hasPermission('market:banner:query')")
    public CommonResult<List<BannerRespVO>> getBannerList() {
        List<BannerDO> list = bannerService.getBannerList();
        return success(BannerConvert.INSTANCE.convertList(list));
    }

}
