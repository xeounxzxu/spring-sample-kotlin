package com.example.base.config.base.item

import org.springframework.stereotype.Component

@Component("myItem")
class MyItem : Item {
    override fun getName(): String = "my_item"
}