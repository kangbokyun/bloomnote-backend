package com.bloomnote.upload.infrastructure.album.repository

import com.bloomnote.upload.infrastructure.album.entity.Album
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface AlbumJpaRepository : JpaRepository<Album, Long> {
    fun findAllByUserIdAndCreatedDateTimeBetween(
        userId: Long,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): List<Album>

    fun findAllByCreatedDateTimeBetween(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
    ): List<Album>
}