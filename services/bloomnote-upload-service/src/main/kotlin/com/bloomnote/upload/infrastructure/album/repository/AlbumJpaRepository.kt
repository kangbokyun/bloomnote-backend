package com.bloomnote.upload.infrastructure.album.repository

import com.bloomnote.upload.infrastructure.album.entity.Album
import org.springframework.data.jpa.repository.JpaRepository

interface AlbumJpaRepository : JpaRepository<Album, Long> {
}