package com.bloomnote.user.presentation.join.controller

import com.bloomnote.response.ResponseNear
import com.bloomnote.user.application.join.usecase.JoinUseCase
import com.bloomnote.user.presentation.join.dto.JoinUserRequestDto
import com.bloomnote.user.presentation.join.dto.JoinUserResponseDto
import com.bloomnote.user.presentation.join.mapper.JoinApiMapper
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JoinController(
    private val useCase: JoinUseCase,
    private val apiMapper: JoinApiMapper
) {
    private val log = KotlinLogging.logger {  }

    @PostMapping("/user/join")
    fun joinUser(
        @RequestBody joinUserRequestDto: JoinUserRequestDto
    ): ResponseNear<JoinUserResponseDto> {
        log.info { "JOIN USER :::: " }
        val command = JoinApiMapper.toDomain(joinRequestDto = joinUserRequestDto)
        val result = useCase.execute(postJoinQuery = command)
        val response = apiMapper.toResponse(joinUserResult = result)
        return ResponseNear(
            statusCode = HttpStatus.OK.value(),
            result = response
        )
    }
}