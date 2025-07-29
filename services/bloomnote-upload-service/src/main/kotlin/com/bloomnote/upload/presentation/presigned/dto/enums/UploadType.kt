package com.bloomnote.upload.presentation.presigned.dto.enums

enum class UploadType(val value: String) {
    BLOOM_PHOTO("/ALBUM/PHOTO"),
    BLOOM_VIDEO("/ALBUM/VIDEO"),
    USER_PROFILE_IMG("/USER_PROFILE"),
    MAIN_PROFILE_IMG("/MAIN_PROFILE"),
}