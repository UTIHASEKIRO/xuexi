package com.zkzl.module.minio.convert;

import java.util.*;

import com.zkzl.framework.common.pojo.PageResult;

import com.zkzl.module.minio.controller.admin.vo.FileCreateReqVO;
import com.zkzl.module.minio.controller.admin.vo.FileExcelVO;
import com.zkzl.module.minio.controller.admin.vo.FileRespVO;
import com.zkzl.module.minio.controller.admin.vo.FileUpdateReqVO;
import com.zkzl.module.minio.vo.FileDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * minio文件 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface FileConvert {

    FileConvert INSTANCE = Mappers.getMapper(FileConvert.class);

    FileDO convert(FileCreateReqVO bean);

    FileDO convert(FileUpdateReqVO bean);

    FileRespVO convert(FileDO bean);

    List<FileRespVO> convertList(List<FileDO> list);

    PageResult<FileRespVO> convertPage(PageResult<FileDO> page);

    List<FileExcelVO> convertList02(List<FileDO> list);

}
