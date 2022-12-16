package com.zkzl.module.pro.dal.mysql.company;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.pro.dal.dataobject.company.CompanyDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.pro.controller.admin.company.vo.*;

/**
 * 公司信息维护 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CompanyMapper extends BaseMapperX<CompanyDO> {

    default PageResult<CompanyDO> selectPage(CompanyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CompanyDO>()
                .orderByDesc(CompanyDO::getId));
    }

    default List<CompanyDO> selectList(CompanyExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CompanyDO>()
                .orderByDesc(CompanyDO::getId));
    }

}
