package com.bloomnote.jwt.domain.repository

import com.bloomnote.jwt.domain.entity.AuthenticateUser
import org.springframework.data.jpa.repository.JpaRepository

interface AuthenticateUserRepository : JpaRepository<AuthenticateUser, Long> {
    fun findByUserEmail(userEmail: String): AuthenticateUser?
}