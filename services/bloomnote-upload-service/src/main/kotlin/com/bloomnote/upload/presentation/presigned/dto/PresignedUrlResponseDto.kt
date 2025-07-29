package com.bloomnote.upload.presentation.presigned.dto

data class PresignedUrlResponseDto(
    val objectKey: String,
    val presignedUrl: String
)
