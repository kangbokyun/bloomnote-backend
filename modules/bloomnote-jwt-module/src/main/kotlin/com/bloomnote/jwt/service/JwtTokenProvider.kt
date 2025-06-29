//package com.bloomnote.jwt.service
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Component
//import java.util.*
//
//const val ACCESS_EXPIRATION_TIME: Long = 1000 * 60 * 1
//
//@Component
//class JwtTokenProvider {
//    @Value("\${jwt.secret}")
//    lateinit var secretKey: String
//
//    private val key by lazy {
//        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
//    }
//
//    // create token
//    fun createAccessToken(authentication: Authentication): TokenInfo {
//        val authorities: String = authentication
//            .authorities
//            .joinToString(
//                separator = ",",
//                transform = GrantedAuthority::getAuthority
//            )
//
//        val now = Date()
//        val accessExpiration = Date(now.time + ACCESS_EXPIRATION_TIME)
//
//        val accessToken = Jwts.builder()
//            .subject(authentication.name)
//            .claim("auth", authorities)
//            .issuedAt(now)
//            .expiration(accessExpiration)
//            .signWith(key)
//            .compact()
//
//        return TokenInfo(accessToken = accessToken)
//    }
//
//    // 토큰 정보 추출
//    fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
//        val claims = getClaims(token = token)
//        val auth = claims["auth"]
//
//        // 권한 정보 추출
//        val authorities = (auth as String)
//            .split(",")
//            .map { SimpleGrantedAuthority(it) }
//
//        val principal: UserDetails = CustomUserResponseDto(
//            userId = 0,
//            userName = claims.subject,
//            password = "",
//            authorities = authorities
//        )
//
//        return UsernamePasswordAuthenticationToken(principal, "", authorities)
//    }
//
//    // 토큰 검증
//    fun validateToken(token: String): Boolean {
//        try {
//            getClaims(token = token)
//            return true
//        } catch (e: Exception) {
//            when (e) {
//                is SecurityException -> {} // Invalid JWT Token
//                is MalformedJwtException -> {} // Invalid JWT Token
//                is ExpiredJwtException -> {} // Expired JWT Token
//                is UnsupportedJwtException -> {} // Unsupported JWT Token
//                is IllegalArgumentException -> {} // JWT claims string is empty
//                else -> {} // else
//            }
//            return false
//        }
//    }
//
//    private fun getClaims(token: String): Claims =
//        Jwts.parser()
//            .verifyWith(key)
//            .build()
//            .parseSignedClaims(token)
//            .payload
//
//}