//package com.bloomnote.jwt.filter
//
//import mu.KotlinLogging
//
//class JwtAuthenticationFilter(
//    private val jwtTokenProvider: JwtTokenProvider
//) : GenericFilterBean() {
//    private val log = KotlinLogging.logger { }
//
//    private val TOKEN_PREFIX = "Bearer"
//
//    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
//        val token = resolveToken(request as HttpServletRequest)
//        token?.takeIf { jwtTokenProvider.validateToken(token) }?.let {
//            val authentication = jwtTokenProvider.getAuthentication(token)
//            SecurityContextHolder.getContext().authentication = authentication
//        }
//
//        chain?.doFilter(request, response)
//    }
//
//    private fun resolveToken(request: HttpServletRequest): String? {
//        val bearerToken = request.getHeader("Authorization")
//
//        return if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
//            bearerToken.substring(7)
//        } else null
//    }
//}