package com.zkzl.module.pro.controller.admin.slidesshow;

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

import com.zkzl.module.pro.controller.admin.slidesshow.vo.*;
import com.zkzl.module.pro.dal.dataobject.slidesshow.SlidesShowDO;
import com.zkzl.module.pro.convert.slidesshow.SlidesShowConvert;
import com.zkzl.module.pro.service.slidesshow.SlidesShowService;

@Api(tags = "管理后台 - 轮播图")
@RestController
@RequestMapping("/pro/slides-show")
@Validated
public class SlidesShowController {

    @Resource
    private SlidesShowService slidesShowService;

    @PostMapping("/create")
    @ApiOperation("创建轮播图")
    @PreAuthorize("@ss.hasPermission('pro:slides-show:create')")
    public CommonResult<Long> createSlidesShow(@Valid @RequestBody SlidesShowCreateReqVO createReqVO) {
        return success(slidesShowService.createSlidesShow(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新轮播图")
    @PreAuthorize("@ss.hasPermission('pro:slides-show:update')")
    public CommonResult<Boolean> updateSlidesShow(@Valid @RequestBody SlidesShowUpdateReqVO updateReqVO) {
        slidesShowService.updateSlidesShow(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除轮播图")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:slides-show:delete')")
    public CommonResult<Boolean> deleteSlidesShow(@RequestParam("id") Long id) {
        slidesShowService.deleteSlidesShow(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得轮播图")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:slides-show:query')")
    public CommonResult<SlidesShowRespVO> getSlidesShow(@RequestParam("id") Long id) {
        SlidesShowDO slidesShow = slidesShowService.getSlidesShow(id);
        return success(SlidesShowConvert.INSTANCE.convert(slidesShow));
    }

    @GetMapping("/list")
    @ApiOperation("获得轮播图列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:slides-show:query')")
    public CommonResult<List<SlidesShowRespVO>> getSlidesShowList(@RequestParam("ids") Collection<Long> ids) {
        List<SlidesShowDO> list = slidesShowService.getSlidesShowList(ids);
        return success(SlidesShowConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得轮播图分页")
    @PreAuthorize("@ss.hasPermission('pro:slides-show:query')")
    public CommonResult<PageResult<SlidesShowRespVO>> getSlidesShowPage(@Valid SlidesShowPageReqVO pageVO) {
        PageResult<SlidesShowDO> pageResult = slidesShowService.getSlidesShowPage(pageVO);
        return success(SlidesShowConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出轮播图 Excel")
    @PreAuthorize("@ss.hasPermission('pro:slides-show:export')")
    @OperateLog(type = EXPORT)
    public void exportSlidesShowExcel(@Valid SlidesShowExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<SlidesShowDO> list = slidesShowService.getSlidesShowList(exportReqVO);
        // 导出 Excel
        List<SlidesShowExcelVO> datas = SlidesShowConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "轮播图.xls", "数据", SlidesShowExcelVO.class, datas);
    }

}
