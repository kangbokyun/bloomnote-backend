package com.bloomnote.user.application.token.service

import com.bloomnote.jwt.domain.repository.AuthenticateUserRepository
import com.bloomnote.jwt.service.JwtTokenProvider
import com.bloomnote.jwt.service.RefreshTokenProvider
import com.bloomnote.user.application.token.mapper.TokenApiMapper
import com.bloomnote.user.application.token.usecase.PostTokenQuery
import com.bloomnote.user.application.token.usecase.TokenResult
import com.bloomnote.user.application.token.usecase.TokenUseCase
import mu.KotlinLogging
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

@Service
class TokenService(
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

        if (!isValidRefreshToken) throw IllegalArgumentException("asd")

        val username = refreshTokenProvider.findTokenClaims(
            refreshToken = postTokenQuery.refreshToken
        )

        val authenticationToken = UsernamePasswordAuthenticationToken(
            username,
            null
        )

        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        log.info { "authentication : $authentication" }
        val user = userRepository.findByUserEmail(userEmail = username!!)
        log.info { "user : $user" }

        val accessToken = accessTokenProvider.createAccessToken(authentication = authentication).accessToken
        val refreshToken = refreshTokenProvider.createRefreshToken(authentication = authentication, userId = user!!.usersId)

        return TokenApiMapper.toResult(
            userId = postTokenQuery.userId,
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
}