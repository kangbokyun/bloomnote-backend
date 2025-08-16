package com.bloomnote.external.feign

import domain.dto.FeignTrendResponseDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(
    name = "bloomnote-upload-service",
    url = "\${feign.service.album}",
)
interface AlbumFeignClient {
    @GetMapping("/trends")
    fun trends(): FeignTrendResponseDto
}