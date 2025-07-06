package com.bloomnote.user.infrastructure.join.repository

import com.bloomnote.user.domain.join.repository.JoinRepository
import com.bloomnote.user.infrastructure.join.entity.Users
import org.springframework.stereotype.Repository

@Repository
class JoinRepositoryImpl(
    private val joinJpaRepository: JoinJpaRepository
) : JoinRepository {
    override fun save(users: Users): Users {
        return joinJpaRepository.save(users)
    }
}