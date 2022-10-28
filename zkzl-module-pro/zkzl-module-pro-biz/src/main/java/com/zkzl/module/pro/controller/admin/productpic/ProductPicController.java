package com.zkzl.module.pro.controller.admin.productpic;

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

import com.zkzl.module.pro.controller.admin.productpic.vo.*;
import com.zkzl.module.pro.dal.dataobject.productpic.ProductPicDO;
import com.zkzl.module.pro.convert.productpic.ProductPicConvert;
import com.zkzl.module.pro.service.productpic.ProductPicService;

@Api(tags = "管理后台 - 产品图片")
@RestController
@RequestMapping("/pro/duct-pic")
@Validated
public class ProductPicController {

    @Resource
    private ProductPicService ductPicService;

    @PostMapping("/create")
    @ApiOperation("创建产品图片")
    @PreAuthorize("@ss.hasPermission('pro:duct-pic:create')")
    public CommonResult<Long> createductPic(@Valid @RequestBody ProductPicCreateReqVO createReqVO) {
        return success(ductPicService.createductPic(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新产品图片")
    @PreAuthorize("@ss.hasPermission('pro:duct-pic:update')")
    public CommonResult<Boolean> updateductPic(@Valid @RequestBody ProductPicUpdateReqVO updateReqVO) {
        ductPicService.updateductPic(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除产品图片")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-pic:delete')")
    public CommonResult<Boolean> deleteductPic(@RequestParam("id") Long id) {
        ductPicService.deleteductPic(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得产品图片")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-pic:query')")
    public CommonResult<ProductPicRespVO> getductPic(@RequestParam("id") Long id) {
        ProductPicDO ductPic = ductPicService.getductPic(id);
        return success(ProductPicConvert.INSTANCE.convert(ductPic));
    }

    @GetMapping("/list")
    @ApiOperation("获得产品图片列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:duct-pic:query')")
    public CommonResult<List<ProductPicRespVO>> getductPicList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductPicDO> list = ductPicService.getductPicList(ids);
        return success(ProductPicConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得产品图片分页")
    @PreAuthorize("@ss.hasPermission('pro:duct-pic:query')")
    public CommonResult<PageResult<ProductPicRespVO>> getductPicPage(@Valid ProductPicPageReqVO pageVO) {
        PageResult<ProductPicDO> pageResult = ductPicService.getductPicPage(pageVO);
        return success(ProductPicConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出产品图片 Excel")
    @PreAuthorize("@ss.hasPermission('pro:duct-pic:export')")
    @OperateLog(type = EXPORT)
    public void exportductPicExcel(@Valid ProductPicExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductPicDO> list = ductPicService.getductPicList(exportReqVO);
        // 导出 Excel
        List<ProductPicExcelVO> datas = ProductPicConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品图片.xls", "数据", ProductPicExcelVO.class, datas);
    }

}
