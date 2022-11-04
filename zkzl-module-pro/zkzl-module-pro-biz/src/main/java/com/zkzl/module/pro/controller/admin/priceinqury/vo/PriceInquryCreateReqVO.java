package com.zkzl.module.pro.controller.admin.priceinqury.vo;

import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.PriceInquryChildCreateReqVO;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 询价创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PriceInquryCreateReqVO extends PriceInquryBaseVO {

    @ApiModelProperty(value = "客户id",required = true)
    @NotNull(message = "客户id不能为空！")
    private Long buyerCompanyId;

    private List<PriceInquryChildCreateReqVO> priceInquryChilds;
}
