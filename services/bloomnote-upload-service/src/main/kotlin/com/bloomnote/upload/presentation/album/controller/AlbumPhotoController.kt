package com.bloomnote.upload.presentation.album.controller

import com.bloomnote.response.ResponseNear
import com.bloomnote.upload.application.album.usecase.AlbumPhotoUseCase
import com.bloomnote.upload.presentation.album.dto.AlbumPhotoRequestDto
import com.bloomnote.upload.presentation.album.dto.AlbumPhotoResponseDto
import com.bloomnote.upload.presentation.album.mapper.PhotoApiMapper
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
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
        val command = PhotoApiMapper.toUploadDomain(requestDto)
        useCase.uploadExecute(command)

        return ResponseNear(statusCode = HttpStatus.OK.value())
    }

    @GetMapping("/album")
    fun findAlbumPhoto(
        @RequestParam userId: Long,
        @RequestParam page: Int,
    ): ResponseNear<AlbumPhotoResponseDto> {
        val command = PhotoApiMapper.toFindAlbumDomain(userId = userId, page = page)
        val result = useCase.findExecute(command)
        val response = PhotoApiMapper.toResponse(result)
        log.info { "response :::: $response" }

        return ResponseNear(
            statusCode = HttpStatus.OK.value(),
            result = response
        )
    }
}