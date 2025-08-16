package com.bloomnote.upload.application.trend.service

import com.bloomnote.upload.application.trend.usecase.TrendResult
import com.bloomnote.upload.application.trend.usecase.TrendUseCase
import com.bloomnote.upload.domain.album.repository.AlbumRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class TrendService(
    private val repository: AlbumRepository,
): TrendUseCase {
    override fun execute(): TrendResult {
        val startDate: LocalDateTime = LocalDate.now().minusDays(7).atTime(LocalTime.MIN)
        val endDate: LocalDateTime = LocalDate.now().atTime(LocalTime.MAX)

        return TrendResult(
            imgList = repository.findAllByCreateDateTimeBetween(
                startDate = startDate,
                endDate = endDate
            ).map { it.objectKey }
        )
    }
}