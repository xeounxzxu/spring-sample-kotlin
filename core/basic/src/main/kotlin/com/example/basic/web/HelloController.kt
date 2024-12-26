package com.example.basic.web

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

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
    fun getHello2(num: Int?): HelloDto {
        when (num) {
            1 -> throw IllegalArgumentException()
            2 -> Thread.sleep(500L)
            3 -> Thread.sleep(900L)
        }
        return HelloDto(kr = "안녕2", en = "hello2")
    }
}

@RestControllerAdvice
class RestControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        value = [
            IllegalStateException::class,
            IllegalArgumentException::class
        ]
    )
    fun error(ex: Exception): String {
        ex.printStackTrace()
        return "error"
    }
}
