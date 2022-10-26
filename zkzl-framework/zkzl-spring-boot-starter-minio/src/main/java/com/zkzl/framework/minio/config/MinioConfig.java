package com.zkzl.framework.minio.config;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.minio")
@Data
public class MinioConfig {
    private String accessKey;

    private String secretKey;

    private String url;

    private String bucketName;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
    }
    /**
     * 初始化MinioTemplate，封装了一些MinIOClient的基本操作
     *
     * @return MinioTemplate
     */
    @Bean(name = "minioTemplate")
    public MinioTemplate minioTemplate() {
        return new MinioTemplate(minioClient(), this);
    }
}
