package com.bloomnote.user.application.token.mapper

import com.bloomnote.user.application.token.usecase.TokenResult

object TokenApiMapper {
    fun toResult(
        userId: Long,
        accessToken: String,
        refreshToken: String,
    ): TokenResult {
        return TokenResult(
            userId = userId,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}