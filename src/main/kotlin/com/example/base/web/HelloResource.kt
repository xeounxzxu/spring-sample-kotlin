package com.example.base.web

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloResource {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/api/hello")
    fun showByString(): String {
        var message = "hello"
        logger.info { "success >> ${message}" }
        return message
    }

}
