package com.bloomnote.upload.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "minio")
data class MinioProperties(
    val url: String,
    val accessUser: String,
    val accessPassword: String,
    val bucketName: String
)
