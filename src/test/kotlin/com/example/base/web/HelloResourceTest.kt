package com.example.base.web


import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class HelloResourceTest {

    @Test
    @DisplayName("hello 단어 프린트 시키기")
    fun printByHello() {
        println("hello = success")
    }

}
