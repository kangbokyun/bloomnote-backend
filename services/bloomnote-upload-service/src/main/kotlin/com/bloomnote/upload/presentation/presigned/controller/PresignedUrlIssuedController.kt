package com.bloomnote.upload.presentation.presigned.controller

import com.bloomnote.response.ResponseNear
import com.bloomnote.upload.application.usecase.UploadUseCase
import com.bloomnote.upload.presentation.presigned.dto.PresignedUrlResponseDto
import com.bloomnote.upload.presentation.presigned.dto.enums.UploadType
import com.bloomnote.upload.presentation.presigned.mapper.UploadApiMapper
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PresignedUrlIssuedController(
    private val useCase: UploadUseCase
) {
    private val log = KotlinLogging.logger { }

    @GetMapping("/presigned/issued/album/photo")
    fun albumPresignedUrlIssued(
        @RequestParam ext: String,
    ): ResponseNear<PresignedUrlResponseDto> {
        log.info { "albumPresignedUrlIssued::: $ext" }
        val command = UploadApiMapper.toDomain(
            ext = ext,
            uploadType = UploadType.BLOOM_PHOTO
        )
        val result = useCase.execute(command)
        val response = UploadApiMapper.toResponse(result)

        return ResponseNear(
            statusCode = HttpStatus.OK.value(),
            result = response
        )
    }
}