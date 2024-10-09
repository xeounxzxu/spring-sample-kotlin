package com.example.client.web

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.Executors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.slf4j.MDCContext
import org.slf4j.MDC
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("mdc")
class MdcTestController {

    @GetMapping("v1")
    fun getV1(
    ) {
        val executor = Executors.newFixedThreadPool(4)
        MDC.put(KEY, "1")
        executor.submit {
            logger.info { MDC.get(KEY) }
        }
    }

    @GetMapping("v2")
    fun getV2(
    ) {
        val executor = Executors.newFixedThreadPool(4)
        MDC.put(KEY, "1")

        val context = MDC.getCopyOfContextMap()
        executor.submit {
            MDC.setContextMap(context) // 현재 work thread 에 mdc 저장
            logger.info { MDC.get(KEY) }
        }
    }

    @GetMapping("v3")
    fun getV3(
    ) {
        // Main Thread add
        MDC.put(KEY, "1")
        logger.info { Thread.currentThread().name }
        runBlocking {

            launch(Dispatchers.Default + MDCContext()) {
                logger.info { MDC.get(KEY) }
            }

            launch(Dispatchers.IO + MDCContext()) {
                logger.info { MDC.get(KEY) }
            }
        }
    }


    companion object {
        const val KEY = "key"
    }
}
