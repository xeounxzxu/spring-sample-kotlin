package com.example.basic.core.exception

import com.example.basic.core.util.I18nMsgUtil
import com.example.basic.core.util.LoggerUtil
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler constructor(
    private val i18nMsgUtil: I18nMsgUtil,
    private val loggerUtil: LoggerUtil,
) {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(
        value = [
            IllegalArgumentException::class
        ]
    )
    fun error(ex: IllegalArgumentException): String {
        ex.printStackTrace()
        return "error"
    }

    // Exception Handler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handler(e: Exception): MsgDTO =
        MsgDTO(
            MsgType.SystemError.getCode(),
            i18nMsgUtil.getMessage(MsgType.SystemError.getCode()),
        ).run {
            loggerUtil.logger.info {
                this.getMessage()
            }
            e.printStackTrace()
            this
        }
}
