package com.example.base.web

import com.example.base.web.dto.TempDTO
import mu.KotlinLogging
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloResource {

    private val logger = KotlinLogging.logger {}

    @GetMapping("/hello")
    fun shoByHello(model: Model): String {
        var tempDTO = TempDTO("vvv", "vvv2")
        model.addAttribute("user", tempDTO)
        return "hello"
    }

    @GetMapping("/api/hello")
    fun showByString(): String {
        var message = "hello"
        logger.info { "success >> ${message}" }
        return message
    }

}
