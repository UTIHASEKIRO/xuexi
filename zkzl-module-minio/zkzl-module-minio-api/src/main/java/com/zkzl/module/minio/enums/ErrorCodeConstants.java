package com.zkzl.module.minio.enums;


import com.zkzl.framework.common.exception.ErrorCode;

/**
 * market 错误码枚举类
 * <p>
 * market 系统，使用 1-003-000-000 段
 */
public interface ErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(20000, "操作成功");
    ErrorCode PARAM_ERROR = new ErrorCode(40000, "参数异常");
    ErrorCode NOT_FOUND = new ErrorCode(40004, "资源不存在");
    ErrorCode FAILURE = new ErrorCode(50000, "系统异常");
    ErrorCode CUSTOM_FAILURE = new ErrorCode(50001, "自定义异常错误");
    ErrorCode ALONE_CHUNK_UPLOAD_SUCCESS = new ErrorCode(20001, "分片上传成功的标识");
    ErrorCode ALL_CHUNK_UPLOAD_SUCCESS = new ErrorCode(20002, "所有的分片均上传成功");
    ErrorCode FILE_NOT_EXISTS = new ErrorCode(40002, "minio文件不存在");
}
