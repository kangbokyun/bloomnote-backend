package com.bloomnote.user.presentation.join.dto

import java.time.LocalDate

data class JoinUserRequestDto(
    val userEmail: String,
    val userPassword: String,
    val userName: String,
    val isParent: Boolean, // 부모라면 아이 정보 받아야 함
    val birth: LocalDate, // 또래는 어디서 뭘 하는지 ai로 추천해주기 위함?
    val gender: String,
    val relation: String, // 부모라면 아이와의 관계
    val profileImg: String? = null,
    val babyInfo: BabyInfo? = null,
) {
    data class BabyInfo(
        val babyName: String,
        val babyProfileImg: String? = null,
        val babyBirth: String,
        val babyGender: String,
    )
}
