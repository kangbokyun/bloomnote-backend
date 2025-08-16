package com.bloomnote.upload.application.trend.usecase

interface TrendUseCase {
    fun execute(): TrendResult
}

data class TrendResult(
    val imgList: List<String>
)