package com.zkzl.module.minio.mapper;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.zkzl.framework.mybatis.core.mapper.BaseMapperX;
import com.zkzl.module.minio.controller.admin.vo.FileExportReqVO;
import com.zkzl.module.minio.controller.admin.vo.FilePageReqVO;
import com.zkzl.module.minio.vo.FileDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * minio文件 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MinioFileMapper extends BaseMapperX<FileDO> {

    default PageResult<FileDO> selectPage(FilePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FileDO>()
                .eqIfPresent(FileDO::getUrl, reqVO.getUrl())
                .eqIfPresent(FileDO::getPath, reqVO.getPath())
                .betweenIfPresent(FileDO::getCreateTime, reqVO.getCreateTime()));
    }

    default List<FileDO> selectList(FileExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<FileDO>()
                .eqIfPresent(FileDO::getUrl, reqVO.getUrl())
                .eqIfPresent(FileDO::getPath, reqVO.getPath())
                .betweenIfPresent(FileDO::getCreateTime, reqVO.getCreateTime()));
    }

}
