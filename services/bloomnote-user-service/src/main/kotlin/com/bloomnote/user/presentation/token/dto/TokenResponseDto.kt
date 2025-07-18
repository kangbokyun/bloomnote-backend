package com.bloomnote.user.presentation.token.dto

data class TokenResponseDto(
    val accessToken: String,
    val refreshToken: String,
)
