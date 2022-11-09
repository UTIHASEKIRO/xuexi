package com.zkzl.module.pro.api.cususer.vo;

import lombok.Data;

@Data
public class CusUserDTO {

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 联系人
     */
    private String contectName;
}
