package com.bloomnote.upload.application.album.mapper

import com.bloomnote.upload.application.album.usecase.PostAlbumPhotoQuery
import com.bloomnote.upload.infrastructure.album.entity.Album

object PhotoApiMapper {
    fun toEntity(albumPhotoQuery: PostAlbumPhotoQuery.AlbumPhotoQuery): Album {
        return Album(
            objectKey = albumPhotoQuery.objectKey,
            volume = albumPhotoQuery.volume,
            userId = albumPhotoQuery.userId,
        )
    }
}