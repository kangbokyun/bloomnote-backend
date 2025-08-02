package com.bloomnote.user.application.token.service

import com.bloomnote.jwt.domain.entity.AuthenticateUser
import com.bloomnote.jwt.domain.repository.AuthenticateUserRepository
import com.bloomnote.jwt.service.JwtTokenProvider
import com.bloomnote.jwt.service.RefreshTokenProvider
import com.bloomnote.user.application.token.mapper.TokenApiMapper
import com.bloomnote.user.application.token.usecase.PostTokenQuery
import com.bloomnote.user.application.token.usecase.TokenResult
import com.bloomnote.user.application.token.usecase.TokenUseCase
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val objectMapper: ObjectMapper,
    private val accessTokenProvider: JwtTokenProvider,
    private val refreshTokenProvider: RefreshTokenProvider,
    private val userRepository: AuthenticateUserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
) : TokenUseCase {
    private val log = KotlinLogging.logger { }
    override fun execute(postTokenQuery: PostTokenQuery): TokenResult {
        val isValidRefreshToken = refreshTokenProvider.validateRefreshToken(
            userId = postTokenQuery.userId,
            refreshToken = postTokenQuery.refreshToken
        )
        if (!isValidRefreshToken) throw IllegalArgumentException("Invalid refresh token")

        val username = refreshTokenProvider.findTokenClaims(
            refreshToken = postTokenQuery.refreshToken
        )
        log.info { "username : $username" }

        // 🔽 직접 사용자 정보 조회 및 인증 객체 생성
        val user = userRepository.findByUserEmail(userEmail = username!!)
            ?: throw IllegalArgumentException("User not found")

        val authentication = UsernamePasswordAuthenticationToken(
            user,
            null,
            listOf(SimpleGrantedAuthority("ROLE_USER"))
        )

        log.info { "authentication : $authentication" }
        log.info { "user : $user" }

        val accessToken = accessTokenProvider.createAccessToken(authentication = authentication).accessToken
        val refreshToken = refreshTokenProvider.createRefreshToken(authentication = authentication, userId = user.usersId)

        return TokenApiMapper.toResult(
            userId = postTokenQuery.userId,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}