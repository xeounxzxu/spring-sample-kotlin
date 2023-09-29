package com.example.base.web

import com.example.base.core.extents.PublicAPI
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class HelloDto(val kr: String = "안녕", val en: String = "hello")

@RestController
@RequestMapping("v1", "hello-prefix")
class HelloController {

    @PublicAPI
    @GetMapping("hello")
    fun getHello(): HelloDto = HelloDto()
}

