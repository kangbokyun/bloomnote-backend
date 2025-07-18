package com.bloomnote.user.presentation.token.controller

import com.bloomnote.response.ResponseNear
import com.bloomnote.user.application.token.usecase.TokenUseCase
import com.bloomnote.user.presentation.token.dto.TokenResponseDto
import com.bloomnote.user.presentation.token.dto.TokenValidateRequestDto
import com.bloomnote.user.presentation.token.mapper.TokenApiMapper
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class TokenController(
    private val useCase: TokenUseCase
) {
    private val log = KotlinLogging.logger { }

    @PostMapping("/token/validate")
    fun tokenValidate(
        @RequestBody tokenValidateRequestDto: TokenValidateRequestDto
    ): ResponseNear<TokenResponseDto> {
        log.info { "tokenValidate ::: $tokenValidateRequestDto" }
        val command = TokenApiMapper.toDomain(tokenValidateRequestDto = tokenValidateRequestDto)
        val result = useCase.execute(postTokenQuery = command)
        val response = TokenApiMapper.toResponse(tokenResult = result)
        return ResponseNear(
            statusCode = HttpStatus.OK.value(),
            result = response
        )
    }
}