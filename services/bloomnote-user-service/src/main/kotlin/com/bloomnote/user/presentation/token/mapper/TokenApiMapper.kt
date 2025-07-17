package com.bloomnote.user.presentation.token.mapper

import com.bloomnote.user.application.token.usecase.PostTokenQuery
import com.bloomnote.user.presentation.token.dto.TokenValidateRequestDto

object TokenApiMapper {
    fun toDomain(tokenValidateRequestDto: TokenValidateRequestDto): PostTokenQuery {
        return PostTokenQuery(
            userId = tokenValidateRequestDto.userId,
            refreshToken = tokenValidateRequestDto.refreshToken
        )
    }
}