package com.example.base.config

import com.example.base.enums.MsgType
import com.example.base.web.dto.MsgDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler {

    // Exception Handler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handler(e: Exception): MsgDTO = MsgDTO(MsgType.SystemError.getCode())
}