package com.zkzl.module.minio.service.file;

import java.util.*;
import javax.validation.*;
import com.zkzl.framework.common.pojo.PageResult;
import com.zkzl.module.minio.controller.admin.vo.FileCreateReqVO;
import com.zkzl.module.minio.controller.admin.vo.FileExportReqVO;
import com.zkzl.module.minio.controller.admin.vo.FilePageReqVO;
import com.zkzl.module.minio.controller.admin.vo.FileUpdateReqVO;
import com.zkzl.module.minio.vo.FileDO;

/**
 * minio文件 Service 接口
 *
 * @author 芋道源码
 */
public interface MinioFileService {

    /**
     * 创建minio文件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    FileDO createFile(@Valid FileCreateReqVO createReqVO);

    /**
     * 更新minio文件
     *
     * @param updateReqVO 更新信息
     */
    void updateFile(@Valid FileUpdateReqVO updateReqVO);

    /**
     * 删除minio文件
     *
     * @param id 编号
     */
    void deleteFile(String id);

    /**
     * 获得minio文件
     *
     * @param id 编号
     * @return minio文件
     */
    FileDO getFile(String id);

}
