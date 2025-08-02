package com.bloomnote.upload.presentation.album.dto

data class AlbumPhotoRequestDto(
    val photoList: List<AlbumPhotoResponse>
) {
    data class AlbumPhotoResponse(
        val userId: Long,
        val objectKey: String,
        val volume: Long,
    )
}
