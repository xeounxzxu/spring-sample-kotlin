package com.example.test

import org.junit.jupiter.api.Test

class PlusTest {

    @Test
    fun plusGivenNullAndNotNullListTest() {
        val a: List<Int> = listOf(1, 2)
        val b: List<Int>? = listOf(1, 2, 3)
        val c = a + b
        println(c)
    }

    @Test
    fun plusGivenNotNullListTest() {
        val a: List<Int> = listOf(1, 2)
        val b: List<Int> = listOf(1, 2, 3)
        val c = a + b
        println(c)
    }
}
