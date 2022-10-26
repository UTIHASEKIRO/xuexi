package com.zkzl.module.system.convert.tenant;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.module.system.controller.admin.permission.vo.role.RoleSimpleRespVO;
import com.zkzl.module.system.controller.admin.tenant.vo.packages.TenantPackageCreateReqVO;
import com.zkzl.module.system.controller.admin.tenant.vo.packages.TenantPackageRespVO;
import com.zkzl.module.system.controller.admin.tenant.vo.packages.TenantPackageSimpleRespVO;
import com.zkzl.module.system.controller.admin.tenant.vo.packages.TenantPackageUpdateReqVO;
import com.zkzl.module.system.dal.dataobject.tenant.TenantPackageDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 租户套餐 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TenantPackageConvert {

    TenantPackageConvert INSTANCE = Mappers.getMapper(TenantPackageConvert.class);

    TenantPackageDO convert(TenantPackageCreateReqVO bean);

    TenantPackageDO convert(TenantPackageUpdateReqVO bean);

    TenantPackageRespVO convert(TenantPackageDO bean);

    List<TenantPackageRespVO> convertList(List<TenantPackageDO> list);

    PageResult<TenantPackageRespVO> convertPage(PageResult<TenantPackageDO> page);

    List<TenantPackageSimpleRespVO> convertList02(List<TenantPackageDO> list);

}
