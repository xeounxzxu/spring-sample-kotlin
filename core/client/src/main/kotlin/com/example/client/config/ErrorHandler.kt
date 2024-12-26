package com.example.client.config

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

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

