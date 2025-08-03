package com.bloomnote.upload.application.album.usecase

import java.time.LocalDateTime

interface AlbumPhotoUseCase {
    fun uploadExecute(postAlbumPhotoQuery: PostAlbumPhotoQuery)
    fun findExecute(getAlbumQuery: GetAlbumQuery): List<AlbumResult>
}

data class PostAlbumPhotoQuery(
    val photoList: List<AlbumPhotoQuery>
) {
    data class AlbumPhotoQuery(
        val userId: Long,
        val objectKey: String,
        val volume: Long,
    )
}

data class GetAlbumQuery(
    val userId: Long,
    val page: Int,
)

data class AlbumResult(
    val userId: Long,
    val albumId: Long,
    val objectKey: String,
    val createdDateTime: LocalDateTime
)