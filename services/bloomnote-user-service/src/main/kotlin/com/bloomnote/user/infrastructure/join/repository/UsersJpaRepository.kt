package com.bloomnote.user.infrastructure.join.repository

import com.bloomnote.user.infrastructure.join.entity.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UsersJpaRepository : JpaRepository<Users, Long> {
}