package com.bloomnote.user.infrastructure.join.entity

import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "users")
class Users(
    val usersId: Long = 0,
    val usersEmail: String,
    val usersPassword: String,
    val isParent: Boolean,
    val userBirth: LocalDate,
    val userGender: String,
    val relationship: String,
    val profileImg: String? = null,
) {
    override fun toString(): String {
        return "Users(usersId=$usersId, usersEmail='$usersEmail', usersPassword='$usersPassword', isParent=$isParent, userBirth=$userBirth, userGender='$userGender', relationship='$relationship', profileImg=$profileImg)"
    }
}