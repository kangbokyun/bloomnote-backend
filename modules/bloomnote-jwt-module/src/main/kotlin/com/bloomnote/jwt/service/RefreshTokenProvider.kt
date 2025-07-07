package com.bloomnote.jwt.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

const val REFRESH_EXPIRATION_TIME: Long = 1000 * 60 * 60

@Component
class RefreshTokenProvider(
//    private val redisTemplate: RedisTemplate<String, Any>
) {
    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    private val key by lazy {
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    // 리프레시 토큰 발급
    fun createRefreshToken(authentication: Authentication): String {
        val now = Date()
        val refreshExpiration = Date(now.time + REFRESH_EXPIRATION_TIME)

        val refreshToken = Jwts.builder()
            .subject(authentication.name)
            .issuedAt(now)
            .expiration(refreshExpiration)
            .signWith(key)
            .compact()

        return refreshToken
    }

    // 리프레시 토큰 검증
//    fun validateRefreshToken(token: String): Boolean {
//        val claims = Jwts.parser()
//            .verifyWith(key)
//            .build()
//            .parseSignedClaims(token)
//
//        val userSubject = claims.payload.subject
//        val redisToken = redisTemplate.opsForValue().get(userSubject)
//
//        return redisToken?.takeIf { it != token }?.let { false } ?: true
//    }
}