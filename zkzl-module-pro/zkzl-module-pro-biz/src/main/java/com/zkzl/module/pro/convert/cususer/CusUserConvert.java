package com.zkzl.module.pro.convert.cususer;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.zkzl.module.pro.controller.admin.cususer.vo.*;
import com.zkzl.module.pro.dal.dataobject.cususer.CusUserDO;

/**
 * 客户公司信息 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface CusUserConvert {

    CusUserConvert INSTANCE = Mappers.getMapper(CusUserConvert.class);

    CusUserDO convert(CusUserCreateReqVO bean);

    CusUserDO convert(CusUserUpdateReqVO bean);

    CusUserRespVO convert(CusUserDO bean);

    List<CusUserRespVO> convertList(List<CusUserDO> list);

    PageResult<CusUserRespVO> convertPage(PageResult<CusUserDO> page);

    List<CusUserExcelVO> convertList02(List<CusUserDO> list);

}
