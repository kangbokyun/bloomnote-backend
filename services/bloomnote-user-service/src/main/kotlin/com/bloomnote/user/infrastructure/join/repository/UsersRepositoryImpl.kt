package com.bloomnote.user.infrastructure.join.repository

import com.bloomnote.user.domain.join.repository.UsersRepository
import com.bloomnote.user.infrastructure.join.entity.Users
import org.springframework.stereotype.Repository

@Repository
class UsersRepositoryImpl(
    private val usersJpaRepository: UsersJpaRepository
) : UsersRepository {
    override fun save(users: Users): Users {
        return usersJpaRepository.save(users)
    }
}