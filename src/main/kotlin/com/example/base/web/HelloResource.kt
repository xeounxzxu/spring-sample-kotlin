package com.example.base.web

import mu.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/hello")
class HelloResource {

    private val logger = KotlinLogging.logger {}

    @GetMapping("")
    fun showByString(): String {
        var message = "hello"
        logger.info { "success >> ${message}" }
        return message
    }

}
