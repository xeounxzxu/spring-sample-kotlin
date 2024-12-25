package com.example.client.web

import com.example.client.clients.HelloClient
import com.example.client.clients.HelloDto
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestController
@RequestMapping("/conn")
class ConnectionController(
    private val helloClient: HelloClient
) {

    @GetMapping
    fun getHello(): HelloDto {
        return helloClient.getHello()
    }

    @GetMapping("async")
    fun getHelloList(): List<HelloDto> {

        return runBlocking {
            listOf(
                async {
                    helloClient.getHello()
                },
                async {
                    helloClient.getHello2()
                }
            ).awaitAll()
        }
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

