package com.bloomnote.user.application.login.mapper

import com.bloomnote.user.application.login.usecase.LoginUserResult
import com.bloomnote.user.infrastructure.join.entity.Users

object LoginApiMapper {
    fun toResult(users: Users, accessToken: String, refreshToken: String): LoginUserResult {
        return LoginUserResult(
            userId = users.usersId,
            userEmail = users.userEmail,
            userName = users.userName,
            profileImg = users.profileImg,
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }
}