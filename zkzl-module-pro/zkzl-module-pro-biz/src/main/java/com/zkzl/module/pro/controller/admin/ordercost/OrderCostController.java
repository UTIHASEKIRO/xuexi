package com.zkzl.module.pro.controller.admin.ordercost;

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

import com.zkzl.module.pro.controller.admin.ordercost.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordercost.OrderCostDO;
import com.zkzl.module.pro.convert.ordercost.OrderCostConvert;
import com.zkzl.module.pro.service.ordercost.OrderCostService;

@Api(tags = "管理后台 - 订单成本")
@RestController
@RequestMapping("/pro/order-cost")
@Validated
public class OrderCostController {

    @Resource
    private OrderCostService orderCostService;

    @PostMapping("/create")
    @ApiOperation("创建订单成本")
    @PreAuthorize("@ss.hasPermission('pro:order-cost:create')")
    public CommonResult<Long> createOrderCost(@Valid @RequestBody OrderCostCreateReqVO createReqVO) {
        return success(orderCostService.createOrderCost(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新订单成本")
    @PreAuthorize("@ss.hasPermission('pro:order-cost:update')")
    public CommonResult<Boolean> updateOrderCost(@Valid @RequestBody OrderCostUpdateReqVO updateReqVO) {
        orderCostService.updateOrderCost(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除订单成本")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:order-cost:delete')")
    public CommonResult<Boolean> deleteOrderCost(@RequestParam("id") Long id) {
        orderCostService.deleteOrderCost(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得订单成本")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:order-cost:query')")
    public CommonResult<OrderCostRespVO> getOrderCost(@RequestParam("id") Long id) {
        OrderCostDO orderCost = orderCostService.getOrderCost(id);
        return success(OrderCostConvert.INSTANCE.convert(orderCost));
    }

    @GetMapping("/list")
    @ApiOperation("获得订单成本列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:order-cost:query')")
    public CommonResult<List<OrderCostRespVO>> getOrderCostList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderCostDO> list = orderCostService.getOrderCostList(ids);
        return success(OrderCostConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得订单成本分页")
    @PreAuthorize("@ss.hasPermission('pro:order-cost:query')")
    public CommonResult<PageResult<OrderCostRespVO>> getOrderCostPage(@Valid OrderCostPageReqVO pageVO) {
        PageResult<OrderCostDO> pageResult = orderCostService.getOrderCostPage(pageVO);
        return success(OrderCostConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出订单成本 Excel")
    @PreAuthorize("@ss.hasPermission('pro:order-cost:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderCostExcel(@Valid OrderCostExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderCostDO> list = orderCostService.getOrderCostList(exportReqVO);
        // 导出 Excel
        List<OrderCostExcelVO> datas = OrderCostConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "订单成本.xls", "数据", OrderCostExcelVO.class, datas);
    }

}
