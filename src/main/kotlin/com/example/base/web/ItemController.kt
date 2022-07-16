package com.example.base.web

import com.example.base.enums.MsgType
import com.example.base.service.ItemService
import com.example.base.utils.I18nMsgUtil
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController constructor(
    private val itemService: ItemService,
    private val i18nMsgUtil: I18nMsgUtil
) {

    @GetMapping("/message")
    fun getMessage() {
        throw NullPointerException()
    }

    @GetMapping("message2")
    fun getMessage(name: String) = i18nMsgUtil.getMessage("test", arrayOf(name))

    @GetMapping("/api/hello")
    fun showByString(): String = "hello"

    @GetMapping("/my-item")
    fun getMyItemName(): String = itemService.getMyItemName()

    @GetMapping("/other-item")
    fun getOtherItem(): String = itemService.getOtherItemName()
}
