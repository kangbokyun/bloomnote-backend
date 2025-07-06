package com.bloomnote.user.presentation.join.mapper

import com.bloomnote.user.application.join.usecase.JoinUserResult
import com.bloomnote.user.application.join.usecase.PostJoinQuery
import com.bloomnote.user.presentation.join.dto.JoinUserRequestDto
import com.bloomnote.user.presentation.join.dto.JoinUserResponseDto

object JoinApiMapper {
    fun toDomain(joinRequestDto: JoinUserRequestDto): PostJoinQuery {
        return PostJoinQuery(
            userEmail = joinRequestDto.userEmail,
            userPassword = joinRequestDto.userPassword,
            userName = joinRequestDto.userName,
            isBaby = joinRequestDto.isParent,
            birth = joinRequestDto.birth,
            gender = joinRequestDto.gender,
            relation = joinRequestDto.relation,
            profileImg = joinRequestDto.profileImg,
        )
    }

    fun toResponse(joinUserResult: JoinUserResult) = JoinUserResponseDto(
        userId = joinUserResult.userId,
        userEmail = joinUserResult.userEmail,
        userName = joinUserResult.userName,
        userNickname = joinUserResult.userNickname,
        isBaby = joinUserResult.isBaby,
        gender = joinUserResult.gender,
        relation = joinUserResult.relation,
        profileImg = joinUserResult.profileImg,
    )
}