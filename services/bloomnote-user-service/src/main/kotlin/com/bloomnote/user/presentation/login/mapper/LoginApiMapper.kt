package com.bloomnote.user.presentation.login.mapper

import com.bloomnote.user.application.login.usecase.LoginUserResult
import com.bloomnote.user.application.login.usecase.PostLoginQuery
import com.bloomnote.user.presentation.login.dto.LoginRequestDto
import com.bloomnote.user.presentation.login.dto.LoginResponseDto

object LoginApiMapper {
    fun toDomain(loginRequestDto: LoginRequestDto): PostLoginQuery {
        return PostLoginQuery(
            userEmail = loginRequestDto.userEmail,
            userPassword = loginRequestDto.userPassword
        )
    }

    fun toResponse(loginUserResult: LoginUserResult): LoginResponseDto {
        return LoginResponseDto(
            userId = loginUserResult.userId,
            userEmail = loginUserResult.userEmail,
            userName = loginUserResult.userName,
//            profileImg = loginUserResult.profileImg,
            accessToken = loginUserResult.accessToken,
            refreshToken = loginUserResult.refreshToken,
        )
    }
}