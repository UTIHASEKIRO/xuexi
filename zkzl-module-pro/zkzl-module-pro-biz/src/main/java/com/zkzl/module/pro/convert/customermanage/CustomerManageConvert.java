package com.zkzl.module.pro.convert.customermanage;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.customermanage.vo.*;
import com.zkzl.module.pro.dal.dataobject.customermanage.CustomerManageDO;

/**
 * 客户管理跟进 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface CustomerManageConvert {

    CustomerManageConvert INSTANCE = Mappers.getMapper(CustomerManageConvert.class);

    CustomerManageDO convert(CustomerManageCreateReqVO bean);

    CustomerManageDO convert(CustomerManageUpdateReqVO bean);

    CustomerManageRespVO convert(CustomerManageDO bean);

    List<CustomerManageRespVO> convertList(List<CustomerManageDO> list);

    PageResult<CustomerManageRespVO> convertPage(PageResult<CustomerManageDO> page);

    List<CustomerManageExcelVO> convertList02(List<CustomerManageDO> list);

}
