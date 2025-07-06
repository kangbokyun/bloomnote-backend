package com.bloomnote.user.application.join.mapper

import com.bloomnote.user.application.join.usecase.JoinUserResult
import com.bloomnote.user.infrastructure.join.entity.Users

object JoinApiMapper {
    fun toResult(users: Users): JoinUserResult {
        return JoinUserResult(
            userId = users.usersId,
            userEmail = users.userEmail,
            userName = users.userName,
            userNickname = users.userNickname,
            isBaby = users.isBaby,
            gender = users.userGender,
            relation = users.relationship,
            profileImg = users.profileImg,
        )
    }
}