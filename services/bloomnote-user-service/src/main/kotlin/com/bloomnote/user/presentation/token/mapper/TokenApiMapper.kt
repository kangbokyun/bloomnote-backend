package com.bloomnote.user.presentation.token.mapper

import com.bloomnote.user.application.token.usecase.PostTokenQuery
import com.bloomnote.user.application.token.usecase.TokenResult
import com.bloomnote.user.presentation.token.dto.TokenResponseDto
import com.bloomnote.user.presentation.token.dto.TokenValidateRequestDto

object TokenApiMapper {
    fun toDomain(tokenValidateRequestDto: TokenValidateRequestDto): PostTokenQuery {
        return PostTokenQuery(
            userId = tokenValidateRequestDto.userId,
            refreshToken = tokenValidateRequestDto.refreshToken
        )
    }

    fun toResponse(tokenResult: TokenResult): TokenResponseDto {
        return TokenResponseDto(
            accessToken = tokenResult.accessToken,
            refreshToken = tokenResult.refreshToken
        )
    }
}