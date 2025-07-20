package com.bloomnote.upload.presentation.presigned.controller

import com.bloomnote.upload.presentation.presigned.dto.enums.UploadType
import com.bloomnote.upload.presentation.presigned.mapper.UploadApiMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PresignedUrlIssuedController {
    @GetMapping("/presigned/issued")
    fun presignedUrlIssued(
        @RequestParam ext: String,
        @RequestParam uploadType: UploadType
    ) {
        val command = UploadApiMapper.toDomain(ext = ext, uploadType = uploadType)
    }
}