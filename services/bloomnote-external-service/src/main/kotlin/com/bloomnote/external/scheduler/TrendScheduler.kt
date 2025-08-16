package com.bloomnote.external.scheduler

import com.bloomnote.external.feign.AlbumFeignClient
import com.bloomnote.external.presentation.mapper.TrendApiMapper
import com.bloomnote.external.scheduler.properties.ImgUrlProperties
import mu.KotlinLogging
import org.springframework.core.ParameterizedTypeReference
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class TrendScheduler(
    private val properties: ImgUrlProperties,
    private val albumFeignClient: AlbumFeignClient,
) {
    private val log = KotlinLogging.logger { }

    @Scheduled(cron = "\${cron.trend}")
    fun trends() {
        val trends = albumFeignClient.trends().imgList.map {
            TrendApiMapper.toImageUrls(
                url = properties.url,
                bucket = properties.bucket,
                objectKey = it
            )
        }

        val webClient = WebClient.create(properties.aiServer)
        val requestBody = mapOf("image_urls" to trends.map { it.url })

        val responseMono = webClient.post()
            .uri("/analyze")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(object : ParameterizedTypeReference<List<Map<String, Any>>>() {})
            .block()  // 블로킹 호출

        val captions = responseMono?.mapNotNull { it["caption"] as? String }
        log.info { "Trend trends: $captions" }
    }
}