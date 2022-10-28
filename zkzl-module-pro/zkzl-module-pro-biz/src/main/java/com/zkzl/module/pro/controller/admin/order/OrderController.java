package com.zkzl.module.pro.controller.admin.order;

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

import com.zkzl.module.pro.controller.admin.order.vo.*;
import com.zkzl.module.pro.dal.dataobject.order.OrderDO;
import com.zkzl.module.pro.convert.order.OrderConvert;
import com.zkzl.module.pro.service.order.OrderService;

@Api(tags = "管理后台 - 订单")
@RestController
@RequestMapping("/pro/order")
@Validated
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    @ApiOperation("创建订单")
    @PreAuthorize("@ss.hasPermission('pro:order:create')")
    public CommonResult<Long> createOrder(@Valid @RequestBody OrderCreateReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新订单")
    @PreAuthorize("@ss.hasPermission('pro:order:update')")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody OrderUpdateReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:order:query')")
    public CommonResult<OrderRespVO> getOrder(@RequestParam("id") Long id) {
        OrderDO order = orderService.getOrder(id);
        return success(OrderConvert.INSTANCE.convert(order));
    }

    @GetMapping("/list")
    @ApiOperation("获得订单列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:order:query')")
    public CommonResult<List<OrderRespVO>> getOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderDO> list = orderService.getOrderList(ids);
        return success(OrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得订单分页")
    @PreAuthorize("@ss.hasPermission('pro:order:query')")
    public CommonResult<PageResult<OrderRespVO>> getOrderPage(@Valid OrderPageReqVO pageVO) {
        PageResult<OrderDO> pageResult = orderService.getOrderPage(pageVO);
        return success(OrderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出订单 Excel")
    @PreAuthorize("@ss.hasPermission('pro:order:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderExcel(@Valid OrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderDO> list = orderService.getOrderList(exportReqVO);
        // 导出 Excel
        List<OrderExcelVO> datas = OrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "订单.xls", "数据", OrderExcelVO.class, datas);
    }

}
