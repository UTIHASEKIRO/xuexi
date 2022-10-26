package com.zkzl.module.test.controller.admin.generate;

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

import com.zkzl.module.test.controller.admin.generate.vo.*;
import com.zkzl.module.test.dal.dataobject.generate.GenerateDO;
import com.zkzl.module.test.convert.generate.GenerateConvert;
import com.zkzl.module.test.service.generate.GenerateService;

@Api(tags = "管理后台 - 测试自动代码生成")
@RestController
@RequestMapping("/test/generate")
@Validated
public class GenerateController {

    @Resource
    private GenerateService generateService;

    @PostMapping("/create")
    @ApiOperation("创建测试自动代码生成")
    @PreAuthorize("@ss.hasPermission('test:generate:create')")
    public CommonResult<Integer> createGenerate(@Valid @RequestBody GenerateCreateReqVO createReqVO) {
        return success(generateService.createGenerate(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新测试自动代码生成")
    @PreAuthorize("@ss.hasPermission('test:generate:update')")
    public CommonResult<Boolean> updateGenerate(@Valid @RequestBody GenerateUpdateReqVO updateReqVO) {
        generateService.updateGenerate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除测试自动代码生成")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('test:generate:delete')")
    public CommonResult<Boolean> deleteGenerate(@RequestParam("id") Integer id) {
        generateService.deleteGenerate(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得测试自动代码生成")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Integer.class)
    @PreAuthorize("@ss.hasPermission('test:generate:query')")
    public CommonResult<GenerateRespVO> getGenerate(@RequestParam("id") Integer id) {
        GenerateDO generate = generateService.getGenerate(id);
        return success(GenerateConvert.INSTANCE.convert(generate));
    }

    @GetMapping("/list")
    @ApiOperation("获得测试自动代码生成列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    //@PreAuthorize("@ss.hasPermission('test:generate:query')")
    public CommonResult<List<GenerateRespVO>> getGenerateList(@RequestParam("ids") Collection<Integer> ids) {
        List<GenerateDO> list = generateService.getGenerateList(ids);
        return success(GenerateConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得测试自动代码生成分页")
    @PreAuthorize("@ss.hasPermission('test:generate:query')")
    public CommonResult<PageResult<GenerateRespVO>> getGeneratePage(@Valid GeneratePageReqVO pageVO) {
        PageResult<GenerateDO> pageResult = generateService.getGeneratePage(pageVO);
        return success(GenerateConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出测试自动代码生成 Excel")
    @PreAuthorize("@ss.hasPermission('test:generate:export')")
    @OperateLog(type = EXPORT)
    public void exportGenerateExcel(@Valid GenerateExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<GenerateDO> list = generateService.getGenerateList(exportReqVO);
        // 导出 Excel
        List<GenerateExcelVO> datas = GenerateConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "测试自动代码生成.xls", "数据", GenerateExcelVO.class, datas);
    }

}
