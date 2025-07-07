package com.bloomnote.jwt.mapper

data class TokenResponseDto(
    val userId: Long,
    val grantType: String = "Bearer ",
    val accessToken: String,
    val refreshToken: String? = null
)
