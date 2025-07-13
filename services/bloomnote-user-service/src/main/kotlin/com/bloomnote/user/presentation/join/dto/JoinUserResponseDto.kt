package com.bloomnote.user.presentation.join.dto

data class JoinUserResponseDto(
    val userId: Long,
    val userEmail: String,
    val userName: String,
    val userNickname: String? = null,
    val isParent: Boolean,
//    val gender: String,
//    val relation: String? = null,
//    val profileImg: String? = null
)