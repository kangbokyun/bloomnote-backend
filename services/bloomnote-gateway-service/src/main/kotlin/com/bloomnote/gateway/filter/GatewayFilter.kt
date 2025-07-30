package com.bloomnote.gateway.filter

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.io.Encoders
import io.jsonwebtoken.security.Keys
import mu.KotlinLogging
import org.apache.http.HttpHeaders
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import javax.crypto.SecretKey

@Component
class GatewayFilter(
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
            val requestToken: MutableList<String>? = request.headers[HttpHeaders.AUTHORIZATION]
            log.info { "GatewayFilter :::: request-uri-path [ ${request.uri.path} ][TOKEN: $requestToken]" }

            if (request.uri.path.contains("/bloomnote/user/token/validate")) {
                log.info { "Bypass JWT check for ${request.uri.path}" }
                return@GatewayFilter chain.filter(exchange) // 필터 우회
            }

            if (requestToken.isNullOrEmpty()) {
                log.info { "Token is Null Or Empty Or BadRequest" }
            } else {
                val token = requestToken[0].split(" ")[1]
                log.info { "TOKEN :::: $token" }

                val tokenCheck = jwsExtractAllClaims(token)
                log.info { "tokenCheck ::: $tokenCheck" }
            }

            chain.filter(exchange)
        }
    }

    private fun jwsExtractAllClaims(token: String): Claims {
        try {
            return Jwts
                .parser()
                .verifyWith(this.decoderSecretKeyToByte())
                .build()
                .parseSignedClaims(token)
                .payload
        } catch (e: ExpiredJwtException) {
            throw JwtException("jwt.token.expire")
        } catch (e: UnsupportedJwtException) {
            throw JwtException("JWT UnsupportedJwtException Exception")
        } catch (e: MalformedJwtException) {
            throw JwtException("JWT MalformedJwtException Exception")
        } catch (e: IllegalArgumentException) {
            throw JwtException("JWT IllegalArgumentException Exception")
        }
    }

    private fun decoderSecretKeyToByte(): SecretKey {
        val secretKeyEncodeBase64 = Encoders.BASE64.encode(secretKey.toByteArray())
        val keyBytes = Decoders.BASE64.decode(secretKeyEncodeBase64)

        return Keys.hmacShaKeyFor(keyBytes)
    }
}