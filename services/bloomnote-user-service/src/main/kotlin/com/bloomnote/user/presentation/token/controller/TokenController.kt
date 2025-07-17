package com.bloomnote.user.presentation.token.controller

import com.bloomnote.user.presentation.token.dto.TokenValidateRequestDto
import com.bloomnote.user.presentation.token.mapper.TokenApiMapper
import mu.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenController {
    private val log = KotlinLogging.logger { }

    @PostMapping("/token/validate")
    fun tokenValidate(
        @RequestBody tokenValidateRequestDto: TokenValidateRequestDto
    ) {
        log.info { "tokenValidate ::: $tokenValidateRequestDto" }
        val command = TokenApiMapper.toDomain(tokenValidateRequestDto = tokenValidateRequestDto)
    }
}