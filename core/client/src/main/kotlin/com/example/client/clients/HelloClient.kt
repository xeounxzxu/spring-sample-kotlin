package com.example.client.clients

import com.example.client.clients.dto.HelloDto
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange


interface HelloClient {

    @GetExchange("/v1/hello")
    fun getHello(): HelloDto

    @GetExchange("/v1/hello2")
    fun getHello2(
        @RequestParam(name = "num")
        num: Int? = null
    ): HelloDto
}


