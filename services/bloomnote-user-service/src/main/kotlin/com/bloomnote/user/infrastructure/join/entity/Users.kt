package com.bloomnote.user.infrastructure.join.entity

import com.bloomnote.util.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.time.LocalDate

@Entity
@Table(name = "users")
class Users(
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

//    @Comment("유저 성별")
//    val userGender: String,

//    @Comment("자식과의 관계")
//    val relationship: String? = null,

//    @Comment("유저 프로필 이미지")
//    val profileImg: String? = null,
) : BaseEntity() {
    override fun toString(): String {
        return """
            
            Users(
                usersId=$usersId, 
                userEmail='$userEmail', 
                userPassword='$userPassword', 
                userName='$userName',
                userNickname=$userNickname, 
                isBaby=$isParent, 
                userBirth=$userBirth 
            )""".trimIndent()
    }
}