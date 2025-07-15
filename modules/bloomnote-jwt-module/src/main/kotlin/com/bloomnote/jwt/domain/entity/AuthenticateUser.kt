package com.bloomnote.jwt.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDate

@Entity
@Table(name = "users")
data class AuthenticateUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("users 테이블 primary key")
    val usersId: Long = 0,

    @Comment("유저 로그인 이메일")
    val userEmail: String,

    @Comment("유저 로그인 비밀번호")
    val userPassword: String,

    @Comment("유저 이름")
    val userName: String,

    @Comment("유저 닉네임")
    val userNickname: String? = null,

    @Comment("자식 유무")
    val isParent: Boolean,

    @Comment("유저 생일")
    val userBirth: LocalDate,
)
