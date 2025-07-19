package com.bloomnote.exception.handler

import com.bloomnote.exception.domain.IllegalArgument
import com.bloomnote.exception.domain.IllegalState
import com.bloomnote.response.ResponseNear
import jakarta.servlet.http.HttpServletRequest
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    private val log = KotlinLogging.logger { }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun illegalArgumentExceptionErrorHandler(
        request: HttpServletRequest,
        e: Exception
    ): ResponseNear<String> {
        val illegalArgument = IllegalArgument.fromState(e.message!!)
        log.info { "illegalStateExceptionErrorHandle Error >>>> ${e.message}" }

        return ResponseNear(
            statusCode = illegalArgument.errorCode,
            result = illegalArgument.state
        )
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = [IllegalStateException::class])
    fun illegalStateExceptionErrorHandler(
        request: HttpServletRequest,
        e: Exception
    ): ResponseNear<String> {
        val illegalState = IllegalState.fromState(e.message!!)
        log.info { "illegalStateExceptionErrorHandle Error >>>> ${e.message}" }

        return ResponseNear(
            statusCode = illegalState.errorCode,
            result = illegalState.state
        )
    }
}