package com.bloomnote.upload.domain.album.repository

import com.bloomnote.upload.infrastructure.album.entity.Album

interface AlbumRepository {
    fun save(album: Album): Album
    fun saveAll(albums: List<Album>): List<Album>
}