package com.bloomnote.upload.application.album.service

import com.bloomnote.upload.application.album.mapper.PhotoApiMapper
import com.bloomnote.upload.application.album.usecase.AlbumPhotoUseCase
import com.bloomnote.upload.application.album.usecase.PostAlbumPhotoQuery
import com.bloomnote.upload.domain.album.repository.AlbumRepository
import org.springframework.stereotype.Service

@Service
class AlbumPhotoService(
    private val repository: AlbumRepository,
) : AlbumPhotoUseCase {
    override fun execute(postAlbumPhotoQuery: PostAlbumPhotoQuery) {
        val entityList = postAlbumPhotoQuery.photoList
            .map { PhotoApiMapper.toEntity(it) }

        repository.saveAll(
            albums = entityList
        )
    }
}