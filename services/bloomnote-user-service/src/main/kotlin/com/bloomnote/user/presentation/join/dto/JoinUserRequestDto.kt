package com.bloomnote.user.presentation.join.dto

import java.time.LocalDate

data class JoinUserRequestDto(
    val userId: String,
    val userPw: String,
    val userName: String,
    val isParent: Boolean, // 부모라면 아이 정보 받아야 함
    val userBirth: LocalDate, // 또래는 어디서 뭘 하는지 ai로 추천해주기 위함?
)