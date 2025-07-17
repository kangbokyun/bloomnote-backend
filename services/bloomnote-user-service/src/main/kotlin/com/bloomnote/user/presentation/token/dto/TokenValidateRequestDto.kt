package com.bloomnote.user.presentation.token.dto

data class TokenValidateRequestDto(
    val userId: Long,
    val refreshToken: String,
)
