package com.zkzl.module.pro.controller.app.order.vo;

import com.zkzl.module.pro.controller.admin.product.vo.ProductBaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ApiModel("app - 订单详情 返回 VO")
@Data
@ToString(callSuper = true)
public class OrderDescVO {

    @ApiModelProperty(value = "询订单表业务id")
    private String orderId;

    @ApiModelProperty(value = "卖方公司名称")
    private String sellerCompanyName;

    @ApiModelProperty(value = "卖方公司地址")
    private String sellerCompanyAddress;

    @ApiModelProperty(value = "卖方联系人")
    private String sellerContact;

    @ApiModelProperty(value = "卖方联系方式")
    private String sellerTel;

    @ApiModelProperty(value = "买方联系人")
    private String buyerContactName;

    @ApiModelProperty(value = "买方地址")
    private String buyerCompanyAddress;

    @ApiModelProperty(value = "买方公司名称")
    private String buyerCompanyName;

    @ApiModelProperty(value = "买方公司联系方式")
    private String buyerMobile;

    @ApiModelProperty(value = "goods")
    private List<OrderDescGoodsVO> goods;

    @ApiModelProperty(value = "子表")
    private List<ProductBaseVO> productBaseVOList;

}
