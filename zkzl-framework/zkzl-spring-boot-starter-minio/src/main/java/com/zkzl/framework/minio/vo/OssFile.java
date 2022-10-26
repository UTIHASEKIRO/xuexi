package com.zkzl.framework.minio.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lyf
 * @description:
 * @version: v1.0
 * @since 2022-05-03 13:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssFile {
    /**
     * OSS 存储时文件路径
     */
    private String ossFilePath;
    /**
     * 原始文件名
     */
    private String originalFileName;
}

