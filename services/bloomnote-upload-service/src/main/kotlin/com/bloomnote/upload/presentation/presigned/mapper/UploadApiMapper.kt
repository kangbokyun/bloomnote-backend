package com.bloomnote.upload.presentation.presigned.mapper

import com.bloomnote.upload.application.usecase.GetUploadQuery
import com.bloomnote.upload.presentation.presigned.dto.enums.UploadType

object UploadApiMapper {
    fun toDomain(ext: String, uploadType: UploadType): GetUploadQuery {
        return GetUploadQuery(
            ext = ext,
            uploadType = uploadType
        )
    }
}