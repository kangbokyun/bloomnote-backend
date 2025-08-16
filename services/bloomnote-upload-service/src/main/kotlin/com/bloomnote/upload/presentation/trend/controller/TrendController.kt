package com.bloomnote.upload.presentation.trend.controller

import com.bloomnote.upload.application.trend.usecase.TrendUseCase
import com.bloomnote.upload.presentation.trend.mapper.TrendApiMapper
import domain.dto.FeignTrendResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TrendController(
    private val useCase: TrendUseCase
) {
    @GetMapping("/trends")
    fun trends(): FeignTrendResponseDto {
        val result = useCase.execute()
        val response = TrendApiMapper.toResponse(result)

        return TrendApiMapper.toFeignResponse(response = response)
    }
}