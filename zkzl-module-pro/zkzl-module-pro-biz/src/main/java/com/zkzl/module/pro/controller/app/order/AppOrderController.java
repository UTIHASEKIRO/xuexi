package com.zkzl.module.pro.controller.app.order;

import com.zkzl.framework.common.exception.ServiceException;
import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.security.core.util.SecurityFrameworkUtils;
import com.zkzl.module.pro.controller.admin.evidence.vo.EvidenceCreateReqVO;
import com.zkzl.module.pro.controller.admin.evidence.vo.EvidenceExcelVO;
import com.zkzl.module.pro.controller.admin.evidence.vo.EvidenceExportReqVO;
import com.zkzl.module.pro.controller.admin.order.vo.OrderPageReqVO;
import com.zkzl.module.pro.controller.app.order.vo.OrderDescVO;
import com.zkzl.module.pro.dal.dataobject.evidence.EvidenceDO;
import com.zkzl.module.pro.dal.dataobject.order.OrderDO;
import com.zkzl.module.pro.service.evidence.EvidenceService;
import com.zkzl.module.pro.service.order.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

import static com.zkzl.framework.common.pojo.CommonResult.error;
import static com.zkzl.framework.common.pojo.CommonResult.success;

@Api(tags = "app - 订单")
@RequestMapping("/pro/app-order")
@RestController
public class AppOrderController {

    //缺订单列表  上传凭证  订单详情
    @Resource
    private OrderService orderService;

    @Resource
    private EvidenceService evidenceService;

    @GetMapping("/pageOrder")
    @ApiOperation("用户端订单列表")
    public CommonResult<PageResult<OrderDO>> pageOrder(OrderPageReqVO param) {
        param.setUserId(SecurityFrameworkUtils.getLoginUserId());
        return success(orderService.appGetOrderPage(param));
    }


    @PostMapping("/uploadBalance")
    @ApiOperation("上传尾款截图")
    public CommonResult<Integer> pageOrder(@Valid @RequestBody EvidenceCreateReqVO param) {
        if (null == param
            || null == param.getBalancePic()
            || null == param.getOrderId()
            || null == param.getPriceInquryId()){
            ServiceException mes = new ServiceException();
            return error(mes.setCode(500)
                    .setMessage("缺少参数！"));
        }
        return success(evidenceService.updateByOrder(param));
    }

    @PostMapping("/upload")
    @ApiOperation("上传定金截图")
    public CommonResult<Long> uploadBalance(@Valid @RequestBody EvidenceCreateReqVO param) {
        if (null == param
            || null == param.getDepositPic()
            || null == param.getOrderId()
            || null == param.getPriceInquryId()){
            ServiceException mes = new ServiceException();
            return error(mes.setCode(500)
                    .setMessage("缺少参数！"));
        }
        EvidenceExportReqVO query = new EvidenceExportReqVO();
        query.setOrderId(param.getOrderId())
                .setPriceInquryId(param.getPriceInquryId());
        EvidenceDO evidenceDO = evidenceService.getOne(query);
        if (null != evidenceDO){
            evidenceDO.setDepositPic(param.getDepositPic());
            evidenceService.updateById(evidenceDO);
            return success(null);
        }
        return success(evidenceService.createEvidence(param));
    }

    @GetMapping("/orderDesc")
    @ApiOperation("订单详情")
    public CommonResult<OrderDescVO> orderDesc(OrderPageReqVO param) {
        return success(orderService.orderDesc(param));
    }


}
