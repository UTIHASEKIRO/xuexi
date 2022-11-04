package com.zkzl.module.pro.controller.admin.priceinqury.vo;

import com.zkzl.module.pro.controller.admin.priceinqurychild.vo.PriceInquryChildUpdateReqVO;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 询价更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PriceInquryUpdateReqVO extends PriceInquryBaseVO {

    @ApiModelProperty(value = "id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

    @ApiModelProperty(value = "客户id")
    private Long buyerCompanyId;

    @ApiModelProperty(value = "询价单子表")
    private List<PriceInquryChildUpdateReqVO> priceInquryChilds;

}
