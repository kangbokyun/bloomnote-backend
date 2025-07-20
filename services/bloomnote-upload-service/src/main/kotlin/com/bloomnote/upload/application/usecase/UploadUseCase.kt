package com.bloomnote.upload.application.usecase

import com.bloomnote.upload.presentation.presigned.dto.enums.UploadType

interface UploadUseCase {
    fun execute(getUploadQuery: GetUploadQuery): PresignedIssuedResult
}

data class GetUploadQuery(
    val ext: String,
    val uploadType: UploadType
)

data class PresignedIssuedResult(
    val presignedUrl: String,
    val objectKey: String
)