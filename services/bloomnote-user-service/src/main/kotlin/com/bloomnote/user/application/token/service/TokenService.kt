package com.bloomnote.user.application.token.service

import com.bloomnote.jwt.service.JwtTokenProvider
import com.bloomnote.jwt.service.RefreshTokenProvider
import com.bloomnote.user.application.token.mapper.TokenApiMapper
import com.bloomnote.user.application.token.usecase.PostTokenQuery
import com.bloomnote.user.application.token.usecase.TokenResult
import com.bloomnote.user.application.token.usecase.TokenUseCase
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val accessTokenProvider: JwtTokenProvider,
    private val refreshTokenProvider: RefreshTokenProvider
) : TokenUseCase {
    override fun execute(postTokenQuery: PostTokenQuery): TokenResult {
        val isValidRefreshToken = refreshTokenProvider.validateRefreshToken(
            userId = postTokenQuery.userId,
            refreshToken = postTokenQuery.refreshToken
        )

        if (!isValidRefreshToken) throw IllegalArgumentException("asd")

        return TokenApiMapper.toResult(
            userId = postTokenQuery.userId,
            accessToken = "",
            refreshToken = ""
        )
    }
}