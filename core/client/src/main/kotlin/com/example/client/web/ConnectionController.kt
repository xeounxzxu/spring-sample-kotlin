package com.example.client.web

import com.example.client.clients.HelloDto
import com.example.client.service.HelloService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/conn")
class ConnectionController(
    private val helloService: HelloService
) {

    @GetMapping
    fun getHello(): HelloDto {
        return helloService.getOne()
    }

    @GetMapping("async")
    fun getHelloList(): List<HelloDto> {
        val data = helloService.getList()
        log.info { "work list" }
        return data
    }

    @GetMapping("async2")
    fun getHelloReturnVoid() {
        log.info { "hello return void work" }
        helloService.getListWithWorkerThread()
        log.info { "hello return void" }
    }
}

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(IllegalStateException::class)
    fun error(e: Exception): ErrorMessage {
        e.printStackTrace()
        return ErrorMessage(
            "000",
            "처리중오류가발생하였습니다."
        )
    }
}

data class ErrorMessage(
    val code: String,
    val message: String
)

