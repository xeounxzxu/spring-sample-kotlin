package com.example.basic.web

import com.example.basic.config.resolver.CustomHeader
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/http-test")
class HttpTestController {

    @GetMapping("")
    fun getCall(
        header: CustomHeader
    ): CustomHeader {
        return header
    }
}
