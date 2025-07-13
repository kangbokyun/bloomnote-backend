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
        val token = resolveToken(request as HttpServletRequest)
        if (httpRequest.requestURI == "/login") {
            chain?.doFilter(request, response)
            return
        }
        if (token.isNullOrBlank().not() && jwtTokenProvider.validateToken(token!!)) {
//        token?.takeIf { jwtTokenProvider.validateToken(token) }?.let {
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