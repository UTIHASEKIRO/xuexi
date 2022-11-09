package com.zkzl.module.pro.controller.admin.cususer.vo;

import com.zkzl.module.system.controller.admin.user.vo.user.UserCreateReqVO;
import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 客户公司信息创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CusUserCreateReqVO extends UserCreateReqVO {

    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "联系地址", required = true)
    @NotNull(message = "联系地址不能为空")
    private String address;

    @ApiModelProperty(value = "公司名称", required = true)
    @NotNull(message = "公司名称不能为空")
    private String companyName;

    @ApiModelProperty(value = "联系人", required = true)
    @NotNull(message = "联系人不能为空")
    private String contectName;

    @ApiModelProperty(value = "营业执照")
    private String businessLicense;
}
