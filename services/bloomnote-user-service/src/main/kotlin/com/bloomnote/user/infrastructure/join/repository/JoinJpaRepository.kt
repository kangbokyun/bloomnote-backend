package com.bloomnote.user.infrastructure.join.repository

import com.bloomnote.user.infrastructure.join.entity.Users
import org.springframework.data.jpa.repository.JpaRepository

interface JoinJpaRepository : JpaRepository<Users, Long> {
}