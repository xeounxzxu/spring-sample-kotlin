package com.example.client.web

import com.example.client.service.AopTestService
import com.example.client.service.Response
import com.example.client.service.ReturnData
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/aop")
class AopTestController(
    private val aopTestService: AopTestService
) {

    @PostMapping("/v1")
    fun getString(@RequestBody requestString: String, num: Int): Response<ReturnData> {
        return aopTestService.getValue(requestString, false  , num)
    }

    @PostMapping("/v2")
    fun getStringV2(@RequestBody requestString: String): Response<String> {
        return aopTestService.getValueV2(requestString, true)
    }

}
