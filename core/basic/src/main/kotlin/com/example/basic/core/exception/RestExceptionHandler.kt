package com.example.basic.core.exception

import com.example.basic.core.util.I18nMsgUtil
import com.example.basic.core.util.LoggerUtil
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler(
    private val i18nMsgUtil: I18nMsgUtil,
    private val loggerUtil: LoggerUtil,
) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): String {
        loggerUtil.logger.error("Bad Request: ${ex.message}", ex)
        return "error"
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception): ResponseEntity<MsgDTO> {
        loggerUtil.logger.error("Internal Server Error: ${e.message}", e)

        val errorResponse = MsgDTO(
            MsgType.SystemError.getCode(),
            i18nMsgUtil.getMessage(MsgType.SystemError.getCode())
        )

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}
