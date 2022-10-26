package com.zkzl.module.minio.controller.admin;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.UUID;
import com.zkzl.framework.common.pojo.CommonResult;
import com.zkzl.framework.minio.config.MinioConfig;
import com.zkzl.framework.minio.config.MinioTemplate;
import com.zkzl.framework.minio.vo.OssFile;
import com.zkzl.module.minio.controller.admin.vo.FileCreateReqVO;
import com.zkzl.module.minio.service.file.MinioFileService;
import com.zkzl.module.minio.util.Md5Util;
import com.zkzl.module.minio.vo.FileDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import static com.zkzl.framework.common.pojo.CommonResult.success;
import static com.zkzl.framework.common.pojo.CommonResult.error;
import static com.zkzl.module.minio.enums.ErrorCodeConstants.FAILURE;
import static com.zkzl.module.minio.enums.ErrorCodeConstants.PARAM_ERROR;
import static com.zkzl.module.minio.enums.ErrorCodeConstants.NOT_FOUND;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


@Api(tags = "MINIO - 文件上传")
@RestController
@RequestMapping("/file/minio")
@Validated
@Slf4j
public class MinIoController {

    @Autowired
    private MinioTemplate minioTemplate;

    @Autowired
    private MinioConfig minioConfig;

    @Autowired
    private MinioFileService minioFileService;


    /**
     * 根据文件大小和文件的md5校验文件是否存在
     * 暂时使用Redis实现，后续需要存入数据库
     * 实现秒传接口
     *
     * @param md5 文件的md5
     * @return 操作是否成功
     */
    @GetMapping(value = "/check")
    @ApiOperation("判断文件是否存在")
    public CommonResult<FileDO> checkFileExists(String md5) {
        FileCreateReqVO fileCreateReqVO = new FileCreateReqVO();
        if (ObjectUtils.isEmpty(md5)) {
            return error(PARAM_ERROR);
        }
        // 先从Redis中查询
        FileDO fileDo = minioFileService.getFile(md5);

        // 文件不存在
        if (ObjectUtils.isEmpty(fileDo)) {
            return error(NOT_FOUND);
        }

        // 文件已经存在了
        return success(fileDo);
    }


    /**
     * 文件上传，适合大文件，集成了分片上传
     */
    @PostMapping(value = "/upload")
    @ApiOperation("文件上传")
    public CommonResult<String> upload(HttpServletRequest req) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;

        // 获得文件分片数据
        MultipartFile file = multipartRequest.getFile("data");

        // 上传过程中出现异常，状态码设置为50000
        if (file == null) {
            return error(FAILURE);
        }
        // 分片第几片
        int index = Integer.parseInt(multipartRequest.getParameter("index"));
        // 总片数
        int total = Integer.parseInt(multipartRequest.getParameter("total"));
        // 获取文件名
        String fileName = multipartRequest.getParameter("name");

        String md5 = multipartRequest.getParameter("md5");

        // 创建文件桶
        minioTemplate.makeBucket(md5);
        String objectName = String.valueOf(index);

        log.info("index: {}, total:{}, fileName:{}, md5:{}, objectName:{}", index, total, fileName, md5, objectName);

        // 当不是最后一片时，上传返回的状态码为20001
        if (index < total) {
            try {
                // 上传文件
                OssFile ossFile = minioTemplate.putChunkObject(file.getInputStream(), md5, objectName);
                log.info("{} upload success {}", objectName, ossFile);

                // 设置上传分片的状态
                return success("20001");
            } catch (Exception e) {
                e.printStackTrace();
                return error(FAILURE);
            }
        } else {
            // 为最后一片时状态码为20002
            try {
                // 上传文件
                minioTemplate.putChunkObject(file.getInputStream(), md5, objectName);

                // 设置上传分片的状态
                return success("20002");
            } catch (Exception e) {
                e.printStackTrace();
                return error(FAILURE);
            }
        }

    }

    /**
     * 文件合并
     *
     * @param shardCount 分片总数
     * @param fileName   文件名
     * @param md5        文件的md5
     * @param fileType   文件类型
     * @param fileSize   文件大小
     * @return 分片合并的状态
     */
    @GetMapping(value = "/merge")
    @ApiOperation("文件合并")
    public CommonResult<FileCreateReqVO> merge(Integer shardCount, String fileName, String md5, String fileType,
                                      Long fileSize) {
        FileCreateReqVO fileCreateReqVO = new FileCreateReqVO();
        String targetBucketName = minioConfig.getBucketName();
        String filenameExtension = StringUtils.getFilenameExtension(fileName);
        String fileNameWithoutExtension = UUID.randomUUID().toString();
        String objectName = fileNameWithoutExtension + "." + filenameExtension;
        try {
            // 查询片数据
            List<String> objectNameList = minioTemplate.listObjectNames(md5);
            if (shardCount != objectNameList.size()) {
                // 失败
                return error(FAILURE);
            } else {
                // 开始合并请求
                minioTemplate.composeObject(md5, targetBucketName, objectName);

                log.info("桶：{} 中的分片文件，已经在桶：{},文件 {} 合并成功", md5, targetBucketName, objectName);

                // 合并成功之后删除对应的临时桶
                minioTemplate.removeBucket(md5, true);
                log.info("删除桶 {} 成功", md5);

                // 计算文件的md5
                String fileMd5 = null;
                try (InputStream inputStream = minioTemplate.getObject(targetBucketName, objectName)) {
                    fileMd5 = Md5Util.calculateMd5(inputStream);
                } catch (IOException e) {
                    log.error("", e);
                }

                // 计算文件真实的类型
                String type = null;
                try (InputStream inputStreamCopy = minioTemplate.getObject(targetBucketName, objectName)) {
                    type = FileTypeUtil.getType(inputStreamCopy);
                } catch (IOException e) {
                    log.error("", e);
                }

                // 并和前台的md5进行对比
                if (!ObjectUtils.isEmpty(fileMd5) && !ObjectUtils.isEmpty(type) && fileMd5.equalsIgnoreCase(md5) && type.equalsIgnoreCase(fileType)) {
                    // 表示是同一个文件, 且文件后缀名没有被修改过
                    String url = minioTemplate.getPresignedObjectUrl(targetBucketName, objectName);

                    // 存入redis中
                    //redisTemplate.boundHashOps(MD5_KEY).put(fileMd5, url);
                    // 存入数据库
                    fileCreateReqVO.setMd5(fileMd5);
                    fileCreateReqVO.setUrl(url);
                    fileCreateReqVO.setPath(targetBucketName+"/"+objectName);
                    minioFileService.createFile(fileCreateReqVO);
                } else {
                    log.error("非法的文件信息: 分片数量:{}, 文件名称:{}, 文件md5:{}, 文件类型:{}, 文件大小:{}",
                            shardCount, fileName, md5, fileType, fileSize);

                    // 并需要删除对象
                    minioTemplate.deleteObject(targetBucketName, objectName);
                    return error(FAILURE);
                }
            }
        } catch (Exception e) {
            log.error("", e);
            minioTemplate.deleteObject(targetBucketName, objectName);
            // 失败
            return error(FAILURE);
        }
        return success(fileCreateReqVO);
    }

    /**
     * 读取文件
     *
     * @param bucket
     * @param fileName
     * @param response
     */
    @GetMapping(value = "/getFile")
    @ApiOperation("读取文件")
    public void getFile(String bucket, String fileName, HttpServletResponse response) {
        try {
            InputStream inputStream = minioTemplate.getObject(bucket, fileName);
            String s = fileType(fileName);
            if ("图片".equals(s)) {//下载的内容添加到这
                log.info("图片");
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            } else if ("视频".equals(s)) {
                log.info("视频");
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String range = request.getHeader("Range");
                byte[] bytes = getBytes(inputStream);
                long contentLength = bytes.length;

                int start = 0, end = 0;
                if (range != null && range.startsWith("bytes=")) {
                    String[] values = range.split("=")[1].split("-");
                    start = Integer.parseInt(values[0]);
                    if (values.length > 1) {
                        end = Integer.parseInt(values[1]);
                    }
                }
                int requestSize = 0;
                if (end != 0 && end > start) {
                    requestSize = end - start + 1;
                } else {
                    requestSize = Integer.MAX_VALUE;
                }

                final int bufferSize = 4096;

                byte[] buffer = new byte[bufferSize];
                // response.setContentType("audio/mpeg"); // mp3
                response.setContentType("video/mp4"); // mp4
                response.setHeader("Accept-Ranges", "bytes");
                response.setHeader("ETag", fileName); // ETag自己定义，缓存的作用，用来标识资源
                response.setHeader("Last-Modified", new Date().toString());
                //第一次请求只返回content length来让客户端请求多次实际数据
                ServletOutputStream out = response.getOutputStream();
                if (range == null) {
                    response.setHeader("Content-length", contentLength + "");
                    out.flush();
                } else {
                    //以后的多次以断点续传的方式来返回视频数据
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);//206
                    long requestStart = 0, requestEnd = 0;
                    String[] ranges = range.split("=");
                    if (ranges.length > 1) {
                        String[] rangeDatas = ranges[1].split("-");
                        requestStart = Integer.parseInt(rangeDatas[0]);
                        if (rangeDatas.length > 1) {
                            requestEnd = Integer.parseInt(rangeDatas[1]);
                        }
                    }
                    long length = 0;
                    if (requestEnd > 0) {
                        length = requestEnd - requestStart + 1;
                        response.setHeader("Content-length", "" + length);
                        response.setHeader("Content-Range", "bytes " + requestStart + "-" + requestEnd + "/" + contentLength);
                    } else {
                        length = contentLength - requestStart;
                        response.setHeader("Content-length", "" + length);
                        response.setHeader("Content-Range", "bytes " + requestStart + "-" + (contentLength - 1) + "/" + contentLength);
                    }
                }
                int needSize = requestSize;
                if (end != 0) {
                    bytes = ArrayUtils.subarray(bytes, start, end + 1);
                }
                int i = 0;
                while (needSize > 0) {
                    int beginIndex = i * bufferSize;
                    int endIndex = (i + 1) * bufferSize;
                    i++;
                    if (endIndex > bytes.length) {
                        endIndex = bytes.length;
                    }
                    buffer = ArrayUtils.subarray(bytes, beginIndex, endIndex);
                    int len = endIndex - beginIndex;
                    if (needSize < buffer.length) {
                        out.write(buffer, 0, needSize);
                    } else {
                        out.write(buffer, 0, len);
                        if (len < buffer.length) {
                            break;
                        }
                    }
                    needSize -= buffer.length;
                }
                out.close();
                return;
            } else {
                response.setContentType("application/octet-stream; charset=UTF-8");
            }
            //否则返回流
            IoUtil.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            log.error("文件读取异常: {}", e.getLocalizedMessage());
        }
    }

    private byte[] getBytes(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int num = inputStream.read(buffer);
            while (num != -1) {
                baos.write(buffer, 0, num);
                num = inputStream.read(buffer);
            }
            baos.flush();
            return baos.toByteArray();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 根据文件名获取文件类型
     *
     * @param fileName
     * @return
     */
    public String fileType(String fileName) {
        if (fileName == null) {
            fileName = "其他";
            return fileName;
        } else {
            // 获取文件后缀名并转化为写，用于后续比较
            String fileType = FilenameUtils.getExtension(fileName);
            // 创建图片类型数组
            String img[] = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
                    "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};
            for (int i = 0; i < img.length; i++) {
                if (img[i].equalsIgnoreCase(fileType)) {
                    return "图片";
                }
            }
            // 创建文档类型数组
            String document[] = {"txt", "doc", "docx", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};
            for (int i = 0; i < document.length; i++) {
                if (document[i].equalsIgnoreCase(fileType)) {
                    return "文档";
                }
            }
            // 创建视频类型数组
            String video[] = {"mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm"};
            for (int i = 0; i < video.length; i++) {
                if (video[i].equalsIgnoreCase(fileType)) {
                    return "视频";
                }
            }
            // 创建音乐类型数组
            String music[] = {"mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
                    "m4a", "vqf"};
            for (int i = 0; i < music.length; i++) {
                if (music[i].equalsIgnoreCase(fileType)) {
                    return "音乐";
                }
            }
            // 创建PDF类型数组
            String pdf[] = {"pdf"};
            for (int i = 0; i < pdf.length; i++) {
                if (pdf[i].equalsIgnoreCase(fileType)) {
                    return "PDF";
                }
            }
            // 创建PPT类型数组
            String ppt[] = {"ppt", "pps", "pot", "pat"};
            for (int i = 0; i < ppt.length; i++) {
                if (ppt[i].equalsIgnoreCase(fileType)) {
                    return "PPT";
                }
            }
            // 创建表格类型数组
            String excel[] = {"xls", "xlsx"};
            for (int i = 0; i < excel.length; i++) {
                if (excel[i].equalsIgnoreCase(fileType)) {
                    return "表格";
                }
            }
            // 创建压缩包型数组
            String zip[] = {"zip", "zipx", "rar", "7z", "alz", "arj", "cab"};
            for (int i = 0; i < zip.length; i++) {
                if (zip[i].equalsIgnoreCase(fileType)) {
                    return "压缩包";
                }
            }
        }
        return "其他";
    }

}
