package com.example.basic.service

import com.example.basic.core.aop.AopExecution
import org.springframework.stereotype.Service

@Service
class AopTestService {

    @AopExecution
    fun getValue(requestString: String, isUse: Boolean, num: Int): Response<ReturnData> {

        val data = ReturnData(
            "000",
            isUse
        )

        if (num == 1){
            throw RuntimeException("test")
        }

        return Response(common = "", data = data)
    }

    @AopExecution
    fun getValueV2(requestString: String, isUse: Boolean): Response<String> {

        val data = ReturnData(
            "000",
            isUse
        )

        return Response(common = "", data = "test")
    }
}

data class Response<T>(
    val common: String,
    val data: T?
)

data class ReturnData(
    val status: String?,
    val isUse: Boolean?
)

data class RequestData(
    val status: String,
    val test1: String?
)
