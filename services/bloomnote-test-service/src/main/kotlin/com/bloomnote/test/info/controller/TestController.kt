package com.bloomnote.test.info.controller

import com.bloomnote.test.info.service.TestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val testService: TestService
) {
    @GetMapping("/bloom/test")
    fun testCalling() {
        testService.testCalling()
    }
}