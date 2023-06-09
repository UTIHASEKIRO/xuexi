package com.zkzl.module.pro.controller.admin.priceinqury;

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

import com.zkzl.module.pro.controller.admin.priceinqury.vo.*;
import com.zkzl.module.pro.dal.dataobject.priceinqury.PriceInquryDO;
import com.zkzl.module.pro.convert.priceinqury.PriceInquryConvert;
import com.zkzl.module.pro.service.priceinqury.PriceInquryService;

@Api(tags = "管理后台 - 询价")
@RestController
@RequestMapping("/pro/price-inqury")
@Validated
public class PriceInquryController {

    @Resource
    private PriceInquryService priceInquryService;


    /*
    * 询价单 状态更新
    * 0初始化状态 客户将产品添加到“购物车”
    * 5客户填好所有产品数量提交询价 需要业务员补充卖方信息
    * 1客户询价后状态  需要管理员即老板报价
    *  客户回应老板报价状态 客户是否接受老板报价
    * 2老板确认后状态  待签约
    * 3客户确认  生成订单
    * 4放弃状态  客户存在恶意询价或老板拒签
    * */
    @PostMapping("/create")
    @ApiOperation("创建询价")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:create')")
    public CommonResult<Long> createPriceInqury(@Valid @RequestBody PriceInquryCreateReqVO createReqVO) {
        return success(priceInquryService.createPriceInqury(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新询价")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:update')")
    public CommonResult<Boolean> updatePriceInqury(@Valid @RequestBody PriceInquryUpdateReqVO updateReqVO) {
        priceInquryService.updatePriceInqury(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除询价")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:delete')")
    public CommonResult<Boolean> deletePriceInqury(@RequestParam("id") Long id) {
        priceInquryService.deletePriceInqury(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得询价")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:query')")
    public CommonResult<PriceInquryAndChildsVO> getPriceInqury(@RequestParam("id") Long id) {
        PriceInquryAndChildsVO priceInqury = priceInquryService.getPriceInquryAndChilds(id);
        return success(priceInqury);
    }

    @GetMapping("/list")
    @ApiOperation("获得询价列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:query')")
    public CommonResult<List<PriceInquryRespVO>> getPriceInquryList(@RequestParam("ids") Collection<Long> ids) {
        List<PriceInquryDO> list = priceInquryService.getPriceInquryList(ids);
        return success(PriceInquryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page-manage")
    @ApiOperation("获得询价分页-管理员")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:query')")
    public CommonResult<PageResult<PriceInquryPageVO>> pageManage(@Valid PriceInquryPageReqVO pageVO) {
        PageResult<PriceInquryPageVO> pageResult = priceInquryService.pageManage(pageVO);
        return success(pageResult);
    }

    @GetMapping("/page-common")
    @ApiOperation("获得询价分页-业务员")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:query')")
    public CommonResult<PageResult<PriceInquryPageVO>> pageCommon(@Valid PriceInquryPageReqVO pageVO) {
        PageResult<PriceInquryPageVO> pageResult = priceInquryService.pageCommon(pageVO);
        return success(pageResult);
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出询价 Excel")
    @PreAuthorize("@ss.hasPermission('pro:price-inqury:export')")
    @OperateLog(type = EXPORT)
    public void exportPriceInquryExcel(@Valid PriceInquryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PriceInquryDO> list = priceInquryService.getPriceInquryList(exportReqVO);
        // 导出 Excel
        List<PriceInquryExcelVO> datas = PriceInquryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "询价.xls", "数据", PriceInquryExcelVO.class, datas);
    }

}
