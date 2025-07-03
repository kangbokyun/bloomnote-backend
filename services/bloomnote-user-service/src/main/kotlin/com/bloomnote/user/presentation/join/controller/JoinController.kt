package com.bloomnote.user.presentation.join.controller

import com.bloomnote.user.presentation.join.dto.JoinUserRequestDto
import com.bloomnote.user.presentation.join.mapper.JoinApiMapper
import mu.KotlinLogging
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class JoinController {
    private val log = KotlinLogging.logger {  }

    @PostMapping("/user/join")
    fun joinUser(
        @RequestBody joinUserRequestDto: JoinUserRequestDto
    ) {
        log.info { "JOIN USER :::: " }
        val command = JoinApiMapper.toDomain(joinRequestDto = joinUserRequestDto)
    }
}