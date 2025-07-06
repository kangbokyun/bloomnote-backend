package com.bloomnote.user.domain.join.repository

import com.bloomnote.user.infrastructure.join.entity.Users

interface JoinRepository {
    fun save(users: Users): Users
}