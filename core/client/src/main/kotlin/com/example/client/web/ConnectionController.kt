package com.example.client.web

import com.example.client.clients.HelloClient
import com.example.client.clients.HelloDto
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

    @GetMapping("")
    fun getPing(): HelloDto {
        return helloClient.getHello()
    }
}

@RestControllerAdvice
class ErrorHandler {
    @ExceptionHandler(Exception::class)
    fun error(e: Exception): String {
        e.printStackTrace()
        return "error"
    }
}

