//package com.bloomnote.user.application.login.service
//
//import com.bloomnote.jwt.mapper.CustomUserJwtResponseDto
//import com.bloomnote.user.domain.login.repository.LoginRepository
//import com.bloomnote.user.infrastructure.join.entity.Users
//import mu.KotlinLogging
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.stereotype.Service
//
//@Service
//class CustomUserDetailService(
//    private val loginRepository: LoginRepository
//) : UserDetailsService {
//    private val log = KotlinLogging.logger { }
//
//    override fun loadUserByUsername(username: String): UserDetails =
//        loginRepository.findByUserEmail(userEmail = username)
//            ?.let {
//                log.info { "===================================== 1" }
//                createUserDetails(it)
//            }
//            ?: throw UsernameNotFoundException("User $username not found")
//
//    private fun createUserDetails(users: Users) = CustomUserJwtResponseDto(
//        userId = users.usersId,
//        userEmail = users.userEmail,
//        password = users.userPassword,
//        authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))
//    )
//}