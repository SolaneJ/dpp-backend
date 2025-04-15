package com.github.solanej.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 对象存储系统配置
 *
 * @since 2025/4/14 23:31
 */
@Configuration
public class ObjectStorageSystemConfig {

    @Value("${MINIO_ACCESS_KEY}")
    private String accessKey;

    @Value("${MINIO_SECRET_KEY}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint("http://101.42.54.40:9000")
                .credentials(accessKey, secretKey)
                .build();
    }
}
