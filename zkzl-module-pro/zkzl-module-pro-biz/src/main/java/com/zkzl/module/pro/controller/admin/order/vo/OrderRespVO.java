package com.zkzl.module.pro.controller.admin.order.vo;

import com.zkzl.module.pro.controller.admin.ordergoods.vo.OrderGoodsBaseVO;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderRespVO extends OrderBaseVO {

    @ApiModelProperty(value = "序号id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

    @ApiModelProperty(value = "买方公司名称", required = true)
    private String buySellerCompanyName;

    @ApiModelProperty(value = "买方联系地址", required = true)
    private String buySellerCompanyAddress;

    @ApiModelProperty(value = "买方联系人", required = true)
    private String buySellerContact;

    @ApiModelProperty(value = "买方联系电话", required = true)
    private String buySellerTel;

    @ApiModelProperty(value = "客户名")
    private String username;

    @ApiModelProperty(value = "子订单", required = true)
    private List<OrderGoodsBaseVO> orderGoodsBaseVOS;
}
