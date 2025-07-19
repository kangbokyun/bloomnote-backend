package com.bloomnote.upload.config

import com.bloomnote.upload.config.properties.MinioProperties
import io.minio.MinioClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MinIoConfig(
    private val minioProperties: MinioProperties
) {
    @Bean
    fun minioClient(): MinioClient = MinioClient.builder()
        .endpoint(minioProperties.url)
        .credentials(
            minioProperties.accessUser,
            minioProperties.accessPassword
        )
        .build()
}