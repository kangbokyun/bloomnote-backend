package com.bloomnote.upload.application.presigned.mapper

import com.bloomnote.upload.application.presigned.usecase.PresignedIssuedResult

object UploadApiMapper {
    fun toResult(presignedUrl: String, objectKey: String): PresignedIssuedResult {
        return PresignedIssuedResult(
            presignedUrl = presignedUrl,
            objectKey = objectKey,
        )
    }
}