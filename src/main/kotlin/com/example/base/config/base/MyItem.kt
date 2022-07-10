package com.example.base.config.base

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
@Qualifier("myItem")
class MyItem : Item {
    override fun getName(): String = "my_item"
}