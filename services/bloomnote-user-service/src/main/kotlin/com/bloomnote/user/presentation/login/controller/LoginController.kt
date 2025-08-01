package com.bloomnote.user.presentation.login.controller

import com.bloomnote.response.ResponseNear
import com.bloomnote.user.application.login.usecase.LoginUseCase
import com.bloomnote.user.presentation.login.dto.LoginRequestDto
import com.bloomnote.user.presentation.login.dto.LoginResponseDto
import com.bloomnote.user.presentation.login.mapper.LoginApiMapper
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    private val useCase: LoginUseCase
) {
    private val log = KotlinLogging.logger {  }

    @PostMapping("/login")
    fun userLogin(
        @RequestBody loginRequestDto: LoginRequestDto
    ): ResponseNear<LoginResponseDto> {
        log.info { "loginRequestDto : $loginRequestDto" }
        val command = LoginApiMapper.toDomain(loginRequestDto = loginRequestDto)
        log.info { "command : $command" }
        val result = useCase.execute(postLoginQuery = command)
        log.info { "result : $result" }
        val response = LoginApiMapper.toResponse(loginUserResult = result)
        log.info { "response : $response" }
        return ResponseNear(
            statusCode = HttpStatus.OK.value(),
            result = response
        )
    }
}