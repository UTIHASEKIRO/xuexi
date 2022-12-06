package com.zkzl.module.pro.controller.admin.news;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.common.pojo.CommonResult;
import static com.zkzl.framework.common.pojo.CommonResult.success;

import com.zkzl.framework.excel.core.util.ExcelUtils;

import com.zkzl.framework.operatelog.core.annotations.OperateLog;
import static com.zkzl.framework.operatelog.core.enums.OperateTypeEnum.*;

import com.zkzl.module.pro.controller.admin.news.vo.*;
import com.zkzl.module.pro.dal.dataobject.news.NewsDO;
import com.zkzl.module.pro.convert.news.NewsConvert;
import com.zkzl.module.pro.service.news.NewsService;

@Api(tags = "管理后台 - 新闻")
@RestController
@RequestMapping("/pro/news")
@Validated
public class NewsController {

    @Resource
    private NewsService newsService;

    @PostMapping("/create")
    @ApiOperation("创建新闻")
    @PreAuthorize("@ss.hasPermission('pro:news:create')")
    public CommonResult<Long> createNews(@Valid @RequestBody NewsCreateReqVO createReqVO) {
        return success(newsService.createNews(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新新闻")
    @PreAuthorize("@ss.hasPermission('pro:news:update')")
    public CommonResult<Boolean> updateNews(@Valid @RequestBody NewsUpdateReqVO updateReqVO) {
        newsService.updateNews(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除新闻")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:news:delete')")
    public CommonResult<Boolean> deleteNews(@RequestParam("id") Long id) {
        newsService.deleteNews(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得新闻")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:news:query')")
    public CommonResult<NewsRespVO> getNews(@RequestParam("id") Long id) {
        NewsDO news = newsService.getNews(id);
        return success(NewsConvert.INSTANCE.convert(news));
    }

    @GetMapping("/list")
    @ApiOperation("获得新闻列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    //@PreAuthorize("@ss.hasPermission('pro:news:query')")
    public CommonResult<List<NewsRespVO>> getNewsList(@RequestParam("ids") Collection<Long> ids) {
        List<NewsDO> list = newsService.getNewsList(ids);
        return success(NewsConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得新闻分页")
    @PreAuthorize("@ss.hasPermission('pro:news:query')")
    public CommonResult<PageResult<NewsRespVO>> getNewsPage(@Valid NewsPageReqVO pageVO) {
        PageResult<NewsDO> pageResult = newsService.getNewsPage(pageVO);
        return success(NewsConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出新闻 Excel")
    @PreAuthorize("@ss.hasPermission('pro:news:export')")
    @OperateLog(type = EXPORT)
    public void exportNewsExcel(@Valid NewsExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<NewsDO> list = newsService.getNewsList(exportReqVO);
        // 导出 Excel
        List<NewsExcelVO> datas = NewsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "新闻.xls", "数据", NewsExcelVO.class, datas);
    }

}
