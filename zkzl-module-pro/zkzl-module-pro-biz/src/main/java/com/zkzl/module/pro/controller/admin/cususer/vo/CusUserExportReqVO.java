package com.zkzl.module.pro.controller.admin.cususer.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import com.zkzl.framework.common.pojo.PageParam;

@ApiModel(value = "管理后台 - 客户公司信息 Excel 导出 Request VO", description = "参数和 CusUserPageReqVO 是一致的")
@Data
public class CusUserExportReqVO {

    @ApiModelProperty(value = "联系地址")
    private String address;

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "联系人")
    private String contectName;

}
