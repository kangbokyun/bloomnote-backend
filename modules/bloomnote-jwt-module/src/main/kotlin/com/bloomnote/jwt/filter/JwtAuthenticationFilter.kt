package com.bloomnote.jwt.filter

import com.bloomnote.jwt.service.JwtTokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {
    private val log = KotlinLogging.logger { }

    private val TOKEN_PREFIX = "Bearer"

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val uri = httpRequest.requestURI

        log.info("uri : $uri")

        // ✅ 인증 필터 우회 경로
        val bypassPaths = setOf(
            "/token/validate",
            "/join",
            "/login"
        )

        if (bypassPaths.any { uri.contains(it) }) {
            log.info { "JwtAuthenticationFilter bypassed for URI: $uri" }
            chain?.doFilter(request, response)
            return
        }

        val token = resolveToken(httpRequest)
        if (!token.isNullOrBlank() && jwtTokenProvider.validateToken(token)) {
            val authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain?.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")

        return if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            bearerToken.substring(7)
        } else null
    }
}