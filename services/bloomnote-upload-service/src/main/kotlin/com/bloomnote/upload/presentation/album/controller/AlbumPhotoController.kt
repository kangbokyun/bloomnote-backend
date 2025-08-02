package com.bloomnote.upload.presentation.album.controller

import com.bloomnote.response.ResponseNear
import com.bloomnote.upload.application.album.usecase.AlbumPhotoUseCase
import com.bloomnote.upload.presentation.album.dto.AlbumPhotoRequestDto
import com.bloomnote.upload.presentation.album.mapper.PhotoApiMapper
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AlbumPhotoController(
    private val useCase: AlbumPhotoUseCase
) {
    private val log = KotlinLogging.logger { }

    @PostMapping("/album/photo")
    fun saveAlbumPhoto(
        @RequestBody requestDto: AlbumPhotoRequestDto
    ): ResponseNear<Nothing> {
        log.info { "save album photo: $requestDto" }
        val command = PhotoApiMapper.toDomain(requestDto)
        useCase.execute(command)

        return ResponseNear(statusCode = HttpStatus.OK.value())
    }
}