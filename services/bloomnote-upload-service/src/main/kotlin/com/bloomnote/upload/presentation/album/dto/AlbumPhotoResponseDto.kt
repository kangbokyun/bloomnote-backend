package com.bloomnote.upload.presentation.album.dto

import java.time.LocalDateTime

data class AlbumPhotoResponseDto(
    val photoList: List<AlbumPhoto>
) {
    data class AlbumPhoto(
        val userId: Long,
        val albumId: Long,
        val objectKey: String,
        val createdDateTime: LocalDateTime,
    )
}
