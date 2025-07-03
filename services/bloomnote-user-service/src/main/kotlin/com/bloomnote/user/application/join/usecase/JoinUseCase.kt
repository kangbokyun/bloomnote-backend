package com.bloomnote.user.application.join.usecase

import java.time.LocalDate

interface JoinUseCase {
    fun execute(postJoinQuery: PostJoinQuery)
}

data class PostJoinQuery(
    val userEmail: String,
    val userPassword: String,
    val userName: String,
    val isParent: Boolean,
    val birth: LocalDate,
    val gender: String,
    val relation: String,
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