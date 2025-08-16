package com.bloomnote.upload.presentation.trend.mapper

import com.bloomnote.upload.application.trend.usecase.TrendResult
import com.bloomnote.upload.presentation.trend.dto.TrendResponseDto
import domain.dto.FeignTrendResponseDto

object TrendApiMapper {
    fun toResponse(result: TrendResult): TrendResponseDto {
        return TrendResponseDto(
            imgList = result.imgList
        )
    }

    fun toFeignResponse(response: TrendResponseDto): FeignTrendResponseDto {
        return FeignTrendResponseDto(
            imgList = response.imgList
        )
    }
}