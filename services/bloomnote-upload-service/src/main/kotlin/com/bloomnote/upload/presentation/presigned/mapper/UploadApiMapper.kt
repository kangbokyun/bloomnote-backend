package com.bloomnote.upload.presentation.presigned.mapper

import com.bloomnote.upload.application.usecase.GetUploadQuery
import com.bloomnote.upload.application.usecase.PresignedIssuedResult
import com.bloomnote.upload.presentation.presigned.dto.PresignedUrlResponseDto
import com.bloomnote.upload.presentation.presigned.dto.enums.UploadType

object UploadApiMapper {
    fun toDomain(ext: String, uploadType: UploadType): GetUploadQuery {
        return GetUploadQuery(
            ext = ext,
            uploadType = uploadType
        )
    }

    fun toResponse(result: PresignedIssuedResult): PresignedUrlResponseDto {
        return PresignedUrlResponseDto(
            objectKey = result.objectKey,
            presignedUrl = result.presignedUrl
        )
    }
}