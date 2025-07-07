package com.bloomnote.user.domain.login.repository

import com.bloomnote.user.infrastructure.join.entity.Users

interface LoginRepository {
    fun findByUserEmail(userEmail: String): Users
}