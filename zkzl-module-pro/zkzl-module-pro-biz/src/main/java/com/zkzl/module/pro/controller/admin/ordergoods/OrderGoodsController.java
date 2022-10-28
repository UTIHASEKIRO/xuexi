package com.zkzl.module.pro.controller.admin.ordergoods;

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

import com.zkzl.module.pro.controller.admin.ordergoods.vo.*;
import com.zkzl.module.pro.dal.dataobject.ordergoods.OrderGoodsDO;
import com.zkzl.module.pro.convert.ordergoods.OrderGoodsConvert;
import com.zkzl.module.pro.service.ordergoods.OrderGoodsService;

@Api(tags = "管理后台 - 订单商品关联表	")
@RestController
@RequestMapping("/pro/order-goods")
@Validated
public class OrderGoodsController {

    @Resource
    private OrderGoodsService orderGoodsService;

    @PostMapping("/create")
    @ApiOperation("创建订单商品关联表	")
    @PreAuthorize("@ss.hasPermission('pro:order-goods:create')")
    public CommonResult<Long> createOrderGoods(@Valid @RequestBody OrderGoodsCreateReqVO createReqVO) {
        return success(orderGoodsService.createOrderGoods(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新订单商品关联表	")
    @PreAuthorize("@ss.hasPermission('pro:order-goods:update')")
    public CommonResult<Boolean> updateOrderGoods(@Valid @RequestBody OrderGoodsUpdateReqVO updateReqVO) {
        orderGoodsService.updateOrderGoods(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除订单商品关联表	")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:order-goods:delete')")
    public CommonResult<Boolean> deleteOrderGoods(@RequestParam("id") Long id) {
        orderGoodsService.deleteOrderGoods(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得订单商品关联表	")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('pro:order-goods:query')")
    public CommonResult<OrderGoodsRespVO> getOrderGoods(@RequestParam("id") Long id) {
        OrderGoodsDO orderGoods = orderGoodsService.getOrderGoods(id);
        return success(OrderGoodsConvert.INSTANCE.convert(orderGoods));
    }

    @GetMapping("/list")
    @ApiOperation("获得订单商品关联表	列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('pro:order-goods:query')")
    public CommonResult<List<OrderGoodsRespVO>> getOrderGoodsList(@RequestParam("ids") Collection<Long> ids) {
        List<OrderGoodsDO> list = orderGoodsService.getOrderGoodsList(ids);
        return success(OrderGoodsConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得订单商品关联表	分页")
    @PreAuthorize("@ss.hasPermission('pro:order-goods:query')")
    public CommonResult<PageResult<OrderGoodsRespVO>> getOrderGoodsPage(@Valid OrderGoodsPageReqVO pageVO) {
        PageResult<OrderGoodsDO> pageResult = orderGoodsService.getOrderGoodsPage(pageVO);
        return success(OrderGoodsConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出订单商品关联表	 Excel")
    @PreAuthorize("@ss.hasPermission('pro:order-goods:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderGoodsExcel(@Valid OrderGoodsExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<OrderGoodsDO> list = orderGoodsService.getOrderGoodsList(exportReqVO);
        // 导出 Excel
        List<OrderGoodsExcelVO> datas = OrderGoodsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "订单商品关联表	.xls", "数据", OrderGoodsExcelVO.class, datas);
    }

}
