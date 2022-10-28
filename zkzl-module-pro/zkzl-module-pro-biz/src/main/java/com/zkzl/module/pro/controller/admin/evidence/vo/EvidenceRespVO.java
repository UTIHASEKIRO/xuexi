package com.zkzl.module.pro.controller.admin.evidence.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 订单凭证信息 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvidenceRespVO extends EvidenceBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

}
