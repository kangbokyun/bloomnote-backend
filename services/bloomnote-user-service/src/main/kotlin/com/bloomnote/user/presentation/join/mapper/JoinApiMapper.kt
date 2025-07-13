package com.bloomnote.user.presentation.join.mapper

import com.bloomnote.user.application.join.usecase.JoinUserResult
import com.bloomnote.user.application.join.usecase.PostJoinQuery
import com.bloomnote.user.presentation.join.dto.JoinUserRequestDto
import com.bloomnote.user.presentation.join.dto.JoinUserResponseDto

object JoinApiMapper {
    fun toDomain(joinRequestDto: JoinUserRequestDto): PostJoinQuery {
        return PostJoinQuery(
            userId = joinRequestDto.userId,
            userPw = joinRequestDto.userPw,
            userName = joinRequestDto.userName,
            isBaby = joinRequestDto.isParent,
            userBirth = joinRequestDto.userBirth,
        )
    }

    fun toResponse(joinUserResult: JoinUserResult) = JoinUserResponseDto(
        userId = joinUserResult.userId,
        userEmail = joinUserResult.userEmail,
        userName = joinUserResult.userName,
        userNickname = joinUserResult.userNickname,
        isParent = joinUserResult.isParent,
//        gender = joinUserResult.gender,
//        relation = joinUserResult.relation,
//        profileImg = joinUserResult.profileImg,
    )
}