package com.bloomnote.upload.presentation.album.mapper

import com.bloomnote.upload.application.album.usecase.PostAlbumPhotoQuery
import com.bloomnote.upload.presentation.album.dto.AlbumPhotoRequestDto

object PhotoApiMapper {
    fun toDomain(photoRequestDto: AlbumPhotoRequestDto): PostAlbumPhotoQuery {
        return PostAlbumPhotoQuery(
            photoList = photoRequestDto.photoList.map {
                PostAlbumPhotoQuery.AlbumPhotoQuery(
                    userId = it.userId,
                    objectKey = it.objectKey,
                    volume = it.volume,
                )
            }
        )
    }
}