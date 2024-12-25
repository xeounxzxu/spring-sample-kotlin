package com.example.basic.web

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

data class HelloDto(val kr: String = "안녕", val en: String = "hello")

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("v1")
class HelloController {

    //    @PublicAPI
    @GetMapping("hello")
    fun getHello(): HelloDto {
//        Thread.sleep(1000L) // 1 초
        log.info {
            "work hello1"
        }
        return HelloDto()
    }

    //    @PublicAPI
    @GetMapping("hello2")
    fun getHello2(): HelloDto {
        Thread.sleep(1000L) // 1 초
        log.info {
            "work hello2"
        }
        return HelloDto(kr = "안녕2", en = "hello2")
    }
}
