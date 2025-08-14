package com.bloomnote.external.scheduler.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cron")
data class SchedulerProperties(
    val trend: String,
)
