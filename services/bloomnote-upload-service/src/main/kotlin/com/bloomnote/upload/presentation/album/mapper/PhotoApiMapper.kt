package com.bloomnote.upload.presentation.album.mapper

import com.bloomnote.upload.application.album.usecase.AlbumResult
import com.bloomnote.upload.application.album.usecase.GetAlbumQuery
import com.bloomnote.upload.application.album.usecase.PostAlbumPhotoQuery
import com.bloomnote.upload.presentation.album.dto.AlbumPhotoRequestDto
import com.bloomnote.upload.presentation.album.dto.AlbumPhotoResponseDto

object PhotoApiMapper {
    fun toUploadDomain(photoRequestDto: AlbumPhotoRequestDto): PostAlbumPhotoQuery {
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

    fun toFindAlbumDomain(userId: Long, page: Int): GetAlbumQuery {
        return GetAlbumQuery(
            userId = userId,
            page = page
        )
    }

    fun toResponse(result: List<AlbumResult>): AlbumPhotoResponseDto {
        return AlbumPhotoResponseDto(
            photoList = result.map {
                AlbumPhotoResponseDto.AlbumPhoto(
                    userId = it.userId,
                    albumId = it.albumId,
                    objectKey = it.objectKey,
                    createdDateTime = it.createdDateTime
                )
            }
        )
    }
}