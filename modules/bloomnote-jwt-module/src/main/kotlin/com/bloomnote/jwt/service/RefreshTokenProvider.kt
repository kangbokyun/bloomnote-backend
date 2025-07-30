package com.bloomnote.jwt.service

import com.bloomnote.jwt.domain.enums.TokenRedisKey
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*

const val REFRESH_EXPIRATION_TIME: Long = 1000 * 60 * 60

@Component
class RefreshTokenProvider(
    private val redisTemplate: RedisTemplate<String, Any>
) {
    @Value("\${jwt.secret}")
    lateinit var secretKey: String

    private val key by lazy {
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    // 리프레시 토큰 발급
    fun createRefreshToken(authentication: Authentication, userId: Long): String {
        val now = Date()
        val refreshExpiration = Date(now.time + REFRESH_EXPIRATION_TIME)

        val refreshToken = Jwts.builder()
            .subject(authentication.name)
            .issuedAt(now)
            .expiration(refreshExpiration)
            .signWith(key)
            .compact()

        redisTemplate.delete(createRefreshTokenKey(userId = userId))
        redisTemplate.opsForValue().set(
            createRefreshTokenKey(
                userId = userId
            ),
            refreshToken
        )

        return refreshToken
    }

    // 리프레시 토큰 검증
    fun validateRefreshToken(userId: Long, refreshToken: String): Boolean {
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(refreshToken)
            // parse 성공: 만료, 시그니처, 포맷 유효

            val redisToken = redisTemplate.opsForValue().get(
                createRefreshTokenKey(
                    userId = userId
                )
            )

            return redisToken != null && redisToken == refreshToken
        }  catch (e: ExpiredJwtException) {
            // 토큰 만료
            return false
        } catch (e: SignatureException) {
            // 서명 불일치 (위조 등)
            return false
        } catch (e: MalformedJwtException) {
            // 토큰 구조 비정상 (포맷 오류)
            return false
        } catch (e: UnsupportedJwtException) {
            // 지원하지 않는 JWT (예: 암호화 알고리즘 안 맞음 등)
            return false
        } catch (e: IllegalArgumentException) {
            // null, 공백 등 부적절한 입력값
            return false
        }
    }

    // 리프레시 토큰 정보 추출
    fun findTokenClaims(refreshToken: String): String? {
        val claims = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(refreshToken)

        return claims.payload.subject
    }

    private fun createRefreshTokenKey(userId: Long): String {
        return "${TokenRedisKey.REFRESH_TOKEN_KEY.key}$userId"
    }
}