package com.bloomnote.upload.infrastructure.album.repository

import com.bloomnote.upload.domain.album.repository.AlbumRepository
import com.bloomnote.upload.infrastructure.album.entity.Album
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class AlbumRepositoryImpl(
    private val albumJpaRepository: AlbumJpaRepository
) : AlbumRepository {
    override fun save(album: Album): Album {
        return albumJpaRepository.save(album)
    }

    override fun saveAll(albums: List<Album>): List<Album> {
        return albumJpaRepository.saveAll(albums)
    }

    override fun findByUserIdAndCreatedDateBetween(
        userId: Long,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): List<Album> {
        return albumJpaRepository.findAllByUserIdAndCreatedDateTimeBetween(
            userId = userId,
            startDate = startDate,
            endDate = endDate
        )
    }
}