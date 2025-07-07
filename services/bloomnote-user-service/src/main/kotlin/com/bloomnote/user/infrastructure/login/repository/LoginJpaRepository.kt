package com.bloomnote.user.infrastructure.login.repository

import com.bloomnote.user.infrastructure.join.entity.Users
import org.springframework.data.jpa.repository.JpaRepository

interface LoginJpaRepository : JpaRepository<Users, Long> {
    fun findByUserEmail(userEmail: String): Users
}