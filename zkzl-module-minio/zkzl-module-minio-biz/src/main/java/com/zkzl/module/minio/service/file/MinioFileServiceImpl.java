package com.zkzl.module.minio.service.file;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zkzl.module.minio.controller.admin.vo.FileCreateReqVO;
import com.zkzl.module.minio.controller.admin.vo.FileExportReqVO;
import com.zkzl.module.minio.controller.admin.vo.FileUpdateReqVO;
import com.zkzl.module.minio.convert.FileConvert;
import com.zkzl.module.minio.mapper.MinioFileMapper;
import com.zkzl.module.minio.vo.FileDO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import java.util.*;;
import static com.zkzl.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.zkzl.module.minio.enums.ErrorCodeConstants.FILE_NOT_EXISTS;


/**
 * minio文件 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MinioFileServiceImpl implements MinioFileService {

    @Resource
    private MinioFileMapper minioFileMapper;

    @Override
    public FileDO createFile(FileCreateReqVO createReqVO) {
        // 插入
        FileDO file = FileConvert.INSTANCE.convert(createReqVO);
        minioFileMapper.insert(file);
        // 返回
        return file;
    }

    @Override
    public void updateFile(FileUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateFileExists(updateReqVO.getMd5());
        // 更新
        FileDO updateObj = FileConvert.INSTANCE.convert(updateReqVO);
        minioFileMapper.updateById(updateObj);
    }

    @Override
    public void deleteFile(String id) {
        // 校验存在
        this.validateFileExists(id);
        // 删除
        minioFileMapper.deleteById(id);
    }

    private void validateFileExists(String id) {
        FileDO fileDO = minioFileMapper.selectOne(new QueryWrapper<FileDO>().lambda().eq(FileDO::getMd5, id));
        if (fileDO == null) {
            throw exception(FILE_NOT_EXISTS);
        }
    }

    @Override
    public FileDO getFile(String id) {
        return minioFileMapper.selectOne(new QueryWrapper<FileDO>().lambda().eq(FileDO::getMd5, id));
    }

}
