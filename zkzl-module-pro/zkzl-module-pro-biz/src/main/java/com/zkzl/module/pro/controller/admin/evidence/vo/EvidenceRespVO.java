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

    @ApiModelProperty(value = "订单状态 0初始化-1待生产-2生产完成-3待装柜-4待开船-5待到港-6最后完成", required = true)
    private String status;
}
