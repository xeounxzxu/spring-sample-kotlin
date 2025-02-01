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

    @GetMapping("hello")
    fun getHello(): HelloDto {
        log.info { "work hello api" }

        Thread.sleep(3000L) // 1 초

        log.info {
            "work hello1"
        }
        return HelloDto()
    }

    @GetMapping("hello2")
    fun getHello2(num: Int?): HelloDto {
        log.info { "work hello 2 api" }
        when (num) {
            1 -> throw IllegalArgumentException()
            2 -> Thread.sleep(500L)
            3 -> Thread.sleep(900L)
//            else -> Thread.sleep(10000L)
            else -> Thread.sleep(5000L)
        }
        return HelloDto(kr = "안녕2", en = "hello2")
    }
}
