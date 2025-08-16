package com.bloomnote.external.scheduler.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "storage")
data class ImgUrlProperties(
    val url: String,
    val bucket: String,
    val aiServer: String,
)
