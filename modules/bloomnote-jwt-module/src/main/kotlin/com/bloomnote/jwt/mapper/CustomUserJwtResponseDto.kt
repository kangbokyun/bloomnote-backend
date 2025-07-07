package com.bloomnote.jwt.mapper

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class CustomUserJwtResponseDto(
    val userId: Long,
    userEmail: String,
    password: String,
    authorities: Collection<GrantedAuthority>
) : User(userEmail, password, authorities)
