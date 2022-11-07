package com.zkzl.module.minio.vo;

import lombok.*;

import java.io.Serializable;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import com.zkzl.framework.mybatis.core.dataobject.BaseDO;

/**
 * minio文件 DO
 *
 * @author 芋道源码
 */
@TableName("minio_file")
@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileDO implements Serializable {

    /**
     * 参数主键
     */
    private String md5;
    /**
     * 下载路径
     */
    private String url;
    /**
     * 相对路径
     */
    private String path;

}
