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
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

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

    @GetMapping("/hugging/test")
    fun analyzeImages(): Mono<Map<String, Any>> {
        log.info { "init" }
        val webClient = WebClient.create("http://58.236.116.237:5000")
        log.info { "webClient : $webClient" }
        val imageUrlList: List<String> = listOf(
            "http://192.168.0.25:9000/bloomnote/ALBUM/PHOTO/0331830d-4862-4298-b3f9-0fe8d4b6a89c.jpg",
            "http://192.168.0.25:9000/bloomnote/ALBUM/PHOTO/e1e01114-8b4a-4301-b1ef-bc77d39c0f06.jpg",
            "http://192.168.0.25:9000/bloomnote/ALBUM/PHOTO/ef6fb6ef-a5ea-45a4-a630-b2acb5813e8d.jpg",
            "http://192.168.0.25:9000/bloomnote/ALBUM/PHOTO/6ee778f4-155f-4184-a22b-ce499b2243b5.jpg",
            "http://192.168.0.25:9000/bloomnote/ALBUM/PHOTO/992cc003-a098-48eb-be78-8782dd70c6ec.jpg",
        )
        log.info { "imageUrlList : $imageUrlList" }
        val requestBody = mapOf("image_urls" to imageUrlList)

        return webClient.post()
            .uri("/analyze")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Map::class.java) as Mono<Map<String, Any>>
    }
}