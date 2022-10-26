package com.zkzl.module.test.dal.mysql.generate;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.test.dal.dataobject.generate.GenerateDO;
import org.apache.ibatis.annotations.Mapper;
import com.zkzl.module.test.controller.admin.generate.vo.*;

/**
 * 测试自动代码生成 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface GenerateMapper extends BaseMapperX<GenerateDO> {

    default PageResult<GenerateDO> selectPage(GeneratePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<GenerateDO>()
                .betweenIfPresent(GenerateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GenerateDO::getId));
    }

    default List<GenerateDO> selectList(GenerateExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<GenerateDO>()
                .betweenIfPresent(GenerateDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(GenerateDO::getId));
    }


}
