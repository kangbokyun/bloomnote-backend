package com.bloomnote.external.presentation.mapper

import com.bloomnote.external.application.usecase.ImageUrl

object TrendApiMapper {
    fun toImageUrls(url: String, bucket: String, objectKey: String): ImageUrl {
        return ImageUrl(
            url = "$url/$bucket$objectKey",
        )
    }
}