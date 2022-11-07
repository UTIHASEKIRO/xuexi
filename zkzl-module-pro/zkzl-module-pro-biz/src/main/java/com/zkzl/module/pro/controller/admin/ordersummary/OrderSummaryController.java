package com.zkzl.module.pro.controller.admin.ordersummary;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

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

import com.zkzl.module.pro.controller.admin.ordersummary.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordersummary.OrderSummaryDO;
import com.zkzl.module.pro.convert.ordersummary.OrderSummaryConvert;
import com.zkzl.module.pro.service.ordersummary.OrderSummaryService;

@Api(tags = "管理后台 - 订单汇总")
@RestController
@RequestMapping("/pro/order-summary")
@Validated
public class OrderSummaryController {

    @Resource
    private OrderSummaryService orderSummaryService;

    @PostMapping("/create")
    @ApiOperation("创建订单汇总")
    @PreAuthorize("@ss.hasPermission('pro:order-summary:create')")
    public CommonResult<Long> createOrderSummary(@Valid @RequestBody OrderSummaryCreateReqVO createReqVO) {
        return success(orderSummaryService.createOrderSummary(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新订单汇总")
    @PreAuthorize("@ss.hasPermission('pro:order-summary:update')")
    public CommonResult<Boolean> updateOrderSummary(@Valid @RequestBody OrderSummaryUpdateReqVO updateReqVO) {
        orderSummaryService.updateOrderSummary(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除订单汇总")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:order-summary:delete')")
    public CommonResult<Boolean> deleteOrderSummary(@RequestParam("id") Long id) {
        orderSummaryService.deleteOrderSummary(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得订单汇总")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:order-summary:query')")
    public CommonResult<OrderSummaryRespVO> getOrderSummary(@RequestParam("id") Long id) {
        OrderSummaryDO orderSummary = orderSummaryService.getOrderSummary(id);
        return success(OrderSummaryConvert.INSTANCE.convert(orderSummary));
    }

    @GetMapping("/list")
    @ApiOperation("获得订单汇总列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:order-summary:query')")
    public CommonResult<List<OrderSummaryRespVO>> getOrderSummaryList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderSummaryDO> list = orderSummaryService.getOrderSummaryList(ids);
        return success(OrderSummaryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得订单汇总分页")
    @PreAuthorize("@ss.hasPermission('pro:order-summary:query')")
    public CommonResult<PageResult<OrderSummaryRespVO>> getOrderSummaryPage(@Valid OrderSummaryPageReqVO pageVO) {
        PageResult<OrderSummaryDO> pageResult = orderSummaryService.getOrderSummaryPage(pageVO);
        return success(OrderSummaryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出订单汇总 Excel")
    @PreAuthorize("@ss.hasPermission('pro:order-summary:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderSummaryExcel(@Valid OrderSummaryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderSummaryDO> list = orderSummaryService.getOrderSummaryList(exportReqVO);
        // 导出 Excel
        List<OrderSummaryExcelVO> datas = OrderSummaryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "订单汇总.xls", "数据", OrderSummaryExcelVO.class, datas);
    }

}
