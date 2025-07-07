package com.bloomnote.user.infrastructure.login.repository

import com.bloomnote.user.domain.login.repository.LoginRepository
import com.bloomnote.user.infrastructure.join.entity.Users
import org.springframework.stereotype.Repository

@Repository
class LoginRepositoryImpl(
    private val loginJpaRepository: LoginJpaRepository
) : LoginRepository {
    override fun findByUserEmail(userEmail: String): Users {
        return loginJpaRepository.findByUserEmail(userEmail = userEmail)
    }
}