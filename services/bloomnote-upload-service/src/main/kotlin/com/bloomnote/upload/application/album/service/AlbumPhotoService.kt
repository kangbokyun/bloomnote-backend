package com.bloomnote.upload.application.album.service

import com.bloomnote.upload.application.album.mapper.PhotoApiMapper
import com.bloomnote.upload.application.album.mapper.PhotoApiMapper.toAlbumResult
import com.bloomnote.upload.application.album.usecase.AlbumPhotoUseCase
import com.bloomnote.upload.application.album.usecase.AlbumResult
import com.bloomnote.upload.application.album.usecase.GetAlbumQuery
import com.bloomnote.upload.application.album.usecase.PostAlbumPhotoQuery
import com.bloomnote.upload.domain.album.repository.AlbumRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class AlbumPhotoService(
    private val repository: AlbumRepository,
) : AlbumPhotoUseCase {
    override fun uploadExecute(postAlbumPhotoQuery: PostAlbumPhotoQuery) {
        val entityList = postAlbumPhotoQuery.photoList
            .map { PhotoApiMapper.toEntity(it) }

        repository.saveAll(
            albums = entityList
        )
    }

    override fun findExecute(getAlbumQuery: GetAlbumQuery): List<AlbumResult> {
        val endDate = LocalDate.now()
            .minusMonths(getAlbumQuery.page.toLong())
            .atTime(LocalTime.MAX)

        val startDate = LocalDate.now()
            .minusMonths(getAlbumQuery.page.toLong() + 1)
            .atTime(LocalTime.MIN)

        return repository.findByUserIdAndCreatedDateBetween(
            userId = getAlbumQuery.userId,
            startDate = startDate,
            endDate = endDate
        ).map { toAlbumResult(it) }
    }
}