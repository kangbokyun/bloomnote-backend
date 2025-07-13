package com.bloomnote.user.application.join.usecase

import java.time.LocalDate

interface JoinUseCase {
    fun execute(postJoinQuery: PostJoinQuery): JoinUserResult
}

data class PostJoinQuery(
    val userId: String,
    val userPw: String,
    val userNickname: String? = null,
    val userName: String,
    val isBaby: Boolean,
    val userBirth: LocalDate,
//    val gender: String,
//    val relation: String,
//    val profileImg: String? = null,
//    val babyInfo: BabyInfo? = null,
)

data class BabyInfo(
    val babyName: String,
    val babyProfileImg: String? = null,
    val babyBirth: String,
    val babyGender: String,
)

data class JoinUserResult(
    val userId: Long,
    val userEmail: String,
    val userName: String,
    val userNickname: String? = null,
    val isParent: Boolean,
//    val gender: String,
//    val relation: String? = null,
//    val profileImg: String? = null
)