package com.bloomnote.user.presentation.login.dto

data class LoginResponseDto(
    val userId: Long,
    val userEmail: String,
    val userName: String,
    val profileImg: String? = null,
    val accessToken: String,
    val refreshToken: String
)
