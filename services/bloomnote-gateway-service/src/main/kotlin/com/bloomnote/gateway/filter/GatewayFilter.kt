package com.bloomnote.gateway.filter

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging
import org.apache.http.HttpHeaders
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.crypto.SecretKey

@Component
class GatewayFilter(
    private val objectMapper: ObjectMapper,
    @Value("\${jwt.secret}") private val secretKey: String
) : AbstractGatewayFilterFactory<com.bloomnote.gateway.filter.GatewayFilter.Config>(Config::class.java) {
    private val log = KotlinLogging.logger { }

    data class Config(
        val baseMessage: String? = null,
        val preLogger: Boolean? = null,
        val postLogger: Boolean? = null
    )

    override fun apply(config: Config?): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request
            val tokenHeaders: MutableList<String>? = request.headers[HttpHeaders.AUTHORIZATION]
            log.info { "GatewayFilter :::: request-uri-path [ ${request.uri.path} ][TOKEN: $tokenHeaders]" }

            val byPassList = listOf(
                "/bloomnote/user/login",
                "/bloomnote/user/signup",
                "/bloomnote/user/token/validate",
                "/bloomnote/upload/hugging/test",
            )
            byPassList.firstOrNull {
                request.uri.path.startsWith(it)
            }?.let { return@GatewayFilter chain.filter(exchange) }

            if (tokenHeaders.isNullOrEmpty()) {
                return@GatewayFilter handleUnAuthorized(exchange, "Token is missing")
            }

            val token = try {
                tokenHeaders[0].split(" ")[1]
            } catch (e: Exception) {
                return@GatewayFilter handleUnAuthorized(exchange, "Token format is invalid")
            }

            try {
                val claims = jwsExtractAllClaims(token)
                log.info { "tokenCheck ::: $claims" }
            } catch (e: ExpiredJwtException) {
                log.warn { "Expired JWT Token: ${e.message}" }
                return@GatewayFilter handleUnAuthorized(exchange, "JWT token expired")
            } catch (e: JwtException) {
                log.warn { "JWT Exception: ${e.message}" }
                return@GatewayFilter handleUnAuthorized(exchange, "JWT token invalid")
            } catch (e: Exception) {
                log.error(e) { "Unexpected error during JWT validation" }
                return@GatewayFilter handleUnAuthorized(exchange, "Authentication error")
            }

            chain.filter(exchange)
        }
    }


    @Throws(
        ClaimJwtException::class,
        ExpiredJwtException::class,
        MalformedJwtException::class,
        PrematureJwtException::class,
        SecurityException::class,
        UnsupportedJwtException::class
    )
    private fun jwsExtractAllClaims(token: String): Claims {
        return Jwts
            .parser()
            .verifyWith(this.decoderSecretKeyToByte())
            .build()
            .parseSignedClaims(token)
            .payload
    }

    private fun decoderSecretKeyToByte(): SecretKey {
//        val secretKeyEncodeBase64 = Encoders.BASE64.encode(secretKey.toByteArray())
        val keyBytes = Decoders.BASE64.decode(secretKey)

        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun handleUnAuthorized(exchange: ServerWebExchange, message: String): Mono<Void> {
        exchange.response.statusCode = HttpStatus.UNAUTHORIZED
        val body = UnAuthorized(statusCode = HttpStatus.UNAUTHORIZED.value(), message = message)

        return exchange.response.writeWith(
            Flux
                .just(
                    exchange
                        .response
                        .bufferFactory()
                        .wrap(
                            objectMapper.writeValueAsString(body).encodeToByteArray()
                        )
                )
        )
    }

    data class UnAuthorized(
        val statusCode: Int,
        val message: String
    )
}