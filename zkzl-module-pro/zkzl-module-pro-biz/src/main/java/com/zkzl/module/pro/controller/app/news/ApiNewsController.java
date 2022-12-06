package com.zkzl.module.pro.controller.app.news;

import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.module.pro.controller.admin.cususer.vo.CusUserCreateReqVO;
import com.zkzl.module.pro.controller.admin.news.vo.NewsPageReqVO;
import com.zkzl.module.pro.controller.admin.news.vo.NewsRespVO;
import com.zkzl.module.pro.convert.news.NewsConvert;
import com.zkzl.module.pro.dal.dataobject.news.NewsDO;
import com.zkzl.module.pro.service.cususer.CusUserService;
import com.zkzl.module.pro.service.news.NewsService;
import com.zkzl.module.system.service.user.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "客户端 - 新闻")
@RestController
@RequestMapping("/news")
@Validated
public class ApiNewsController {

    @Resource
    private NewsService newsService;

    @GetMapping("/page")
    @ApiOperation("新闻page")
    public CommonResult<PageResult<NewsRespVO>> getNewsPage(@Valid NewsPageReqVO pageVO) {
        PageResult<NewsDO> pageResult = newsService.getNewsPage(pageVO);
        return success(NewsConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/get")
    @ApiOperation("获得新闻")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    public CommonResult<NewsRespVO> getNews(@RequestParam("id") Long id) {
        NewsDO news = newsService.getNews(id);
        return success(NewsConvert.INSTANCE.convert(news));
    }
}
