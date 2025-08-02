package com.bloomnote.upload.application.album.usecase

interface AlbumPhotoUseCase {
    fun execute(postAlbumPhotoQuery: PostAlbumPhotoQuery)
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