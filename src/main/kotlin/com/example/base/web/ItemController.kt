package com.example.base.web

import com.example.base.service.ItemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController constructor(
    private val itemService: ItemService
) {

    @GetMapping("/test")
    fun test() {
        throw NullPointerException()
    }

    @GetMapping("/api/hello")
    fun showByString(): String = "hello"

    @GetMapping("/my-item")
    fun getMyItemName(): String = itemService.getMyItemName()

    @GetMapping("/other-item")
    fun getOtherItem(): String = itemService.getOtherItemName()
}
