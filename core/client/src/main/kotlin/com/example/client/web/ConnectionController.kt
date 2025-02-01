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

    @GetMapping("test2")
    fun getHelloList() {
        helloService.getList()
    }


    @GetMapping("test1")
    fun getHelloList2() {
//        helloService.getListWithThread()
    }

    @GetMapping("async2")
    fun getHelloReturnVoid() {
        log.info { "hello return void work" }
//        helloService.get()
        log.info { "hello return void" }
    }
}
