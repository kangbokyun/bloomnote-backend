package com.bloomnote.upload.presentation.presigned.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PresignedUrlIssuedController {
    @GetMapping("/presigned/issued")
    fun presignedUrlIssued(
        @RequestParam ext: String
    ) {

    }
}