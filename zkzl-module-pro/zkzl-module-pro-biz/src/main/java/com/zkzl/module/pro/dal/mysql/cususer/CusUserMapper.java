package com.zkzl.module.pro.dal.mysql.cususer;

import java.util.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zkzl.framework.common.pojo.PageParam;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.cususer.CusUserDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.cususer.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 客户公司信息 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CusUserMapper extends BaseMapperX<CusUserDO> {

    default PageResult<CusUserDO> selectPage(CusUserPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CusUserDO>()
                .eqIfPresent(CusUserDO::getAddress, reqVO.getAddress())
                .likeIfPresent(CusUserDO::getCompanyName, reqVO.getCompanyName())
                .likeIfPresent(CusUserDO::getContectName, reqVO.getContectName())
                .orderByDesc(CusUserDO::getUserId));
    }

    default List<CusUserDO> selectList(CusUserExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CusUserDO>()
                .eqIfPresent(CusUserDO::getAddress, reqVO.getAddress())
                .likeIfPresent(CusUserDO::getCompanyName, reqVO.getCompanyName())
                .likeIfPresent(CusUserDO::getContectName, reqVO.getContectName())
                .orderByDesc(CusUserDO::getUserId));
    }

    /*获取客户角色*/
    IPage<CusUserDO> cusFromSysUser(IPage<CusUserDO> pageParam, @Param("pageVO") CusUserPageReqVO pageVO);
}
