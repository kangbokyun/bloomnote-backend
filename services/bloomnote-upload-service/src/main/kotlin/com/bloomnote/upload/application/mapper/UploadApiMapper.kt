package com.bloomnote.upload.application.mapper

import com.bloomnote.upload.application.usecase.PresignedIssuedResult

object UploadApiMapper {
    fun toResult(presignedUrl: String, objectKey: String): PresignedIssuedResult {
        return PresignedIssuedResult(
            presignedUrl = presignedUrl,
            objectKey = objectKey,
        )
    }
}