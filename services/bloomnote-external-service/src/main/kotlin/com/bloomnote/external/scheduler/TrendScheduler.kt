package com.bloomnote.external.scheduler

import com.bloomnote.external.scheduler.properties.SchedulerProperties
import org.springframework.stereotype.Component

@Component
class TrendScheduler(
    private val properties: SchedulerProperties
) {
}