package com.bloomnote.user.application.token.usecase

interface TokenUseCase {
    fun execute(postTokenQuery: PostTokenQuery): TokenResult
}

data class PostTokenQuery(
    val userId: Long,
    val refreshToken: String
)

data class TokenResult(
    val userId: Long,
    val accessToken: String,
    val refreshToken: String
)