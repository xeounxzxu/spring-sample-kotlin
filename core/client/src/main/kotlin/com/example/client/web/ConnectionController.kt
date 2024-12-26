package com.example.client.web

import com.example.client.clients.dto.HelloDto
import com.example.client.service.HelloService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
