package com.bloomnote.user.presentation.join.mapper

import com.bloomnote.user.application.join.usecase.PostJoinQuery
import com.bloomnote.user.presentation.join.dto.JoinUserRequestDto

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
}