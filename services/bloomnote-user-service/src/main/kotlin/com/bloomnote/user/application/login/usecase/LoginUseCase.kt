package com.bloomnote.user.application.login.usecase

interface LoginUseCase {
    fun execute(postLoginQuery: PostLoginQuery): LoginUserResult
}

data class PostLoginQuery(
    val userEmail: String,
    val userPassword: String
)

data class LoginUserResult(
    val userId: Long,
    val userEmail: String,
    val userName: String,
    val profileImg: String? = null,
    val accessToken: String,
    val refreshToken: String,
)

data class CustomUserResponseDto(
    val userId: Long,
    val userEmail: String,
    val userPassword: String
)