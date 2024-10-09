package com.example.test

import io.kotest.matchers.shouldBe
import java.util.concurrent.Executors
import org.junit.jupiter.api.Test
import org.slf4j.MDC


class MdcTest {

    @Test
    fun useToMdcTest() {
        MDC.put("test", "1")
        val mdc = MDC.get("test")
        mdc shouldBe "1"
    }

    @Test
    fun useToThreadPoolTest() {

        // main 스레드에서 MDC put -> 1

        val executor = Executors.newFixedThreadPool(4)
        val expected = "1"
        MDC.put(KEY, expected)
        println(Thread.currentThread().name)

        executor.submit {
            val value = MDC.get(KEY)
            println(Thread.currentThread().name)
            value shouldBe expected
        }

    }

    companion object {
        private const val KEY = "NUM"
    }
}
