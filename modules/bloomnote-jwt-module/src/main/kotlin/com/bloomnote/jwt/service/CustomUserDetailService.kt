package com.bloomnote.jwt.service

import com.bloomnote.jwt.domain.entity.AuthenticateUser
import com.bloomnote.jwt.domain.repository.AuthenticateUserRepository
import com.bloomnote.jwt.mapper.CustomUserJwtResponseDto
import mu.KotlinLogging
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(
    private val loginRepository: AuthenticateUserRepository
) : UserDetailsService {
    private val log = KotlinLogging.logger { }

    override fun loadUserByUsername(username: String): UserDetails =
        loginRepository.findByUserEmail(userEmail = username)
            ?.let { createUserDetails(it) }
            ?: throw UsernameNotFoundException("User $username not found")

    private fun createUserDetails(users: AuthenticateUser) = CustomUserJwtResponseDto(
        userId = users.usersId,
        userEmail = users.userEmail,
        password = users.userPassword,
        authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))
    )
}