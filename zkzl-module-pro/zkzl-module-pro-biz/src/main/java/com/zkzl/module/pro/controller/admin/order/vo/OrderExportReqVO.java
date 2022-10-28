package com.zkzl.module.pro.controller.admin.order.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static com.zkzl.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 订单 Excel 导出 Request VO", description = "参数和 OrderPageReqVO 是一致的")
@Data
public class OrderExportReqVO {

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "卖方公司名称")
    private String sellerCompanyName;

    @ApiModelProperty(value = "卖方联系地址")
    private String sellerCompanyAddress;

    @ApiModelProperty(value = "卖方联系人")
    private String sellerContact;

    @ApiModelProperty(value = "卖方联系电话")
    private String sellerTel;

    @ApiModelProperty(value = "客户id")
    private Long userId;

    @ApiModelProperty(value = "总价格")
    private BigDecimal price;

    @ApiModelProperty(value = "折扣")
    private BigDecimal discount;

    @ApiModelProperty(value = "合计")
    private BigDecimal total;

    @ApiModelProperty(value = "报价日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] priceDate;

    @ApiModelProperty(value = "报价有效日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] effectiveDate;

    @ApiModelProperty(value = "说明")
    private String explain;

    @ApiModelProperty(value = "1待生产-2生产完成-3待装柜-4待开船-5待到港-6最后完成")
    private String status;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
