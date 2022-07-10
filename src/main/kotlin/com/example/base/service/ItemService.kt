package com.example.base.service

import com.example.base.config.base.item.Item
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class ItemService constructor(
    @Qualifier("myItem") private val myItem: Item, @Qualifier("otherItem") private val otherItem: Item
) {

    fun getMyItemName(): String = myItem.getName()

    fun getOtherItemName(): String = otherItem.getName()
}