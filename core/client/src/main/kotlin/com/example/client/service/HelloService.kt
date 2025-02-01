package com.example.client.service

import com.example.client.clients.HelloClient
import com.example.client.clients.HelloClient2
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class HelloService(
    private val helloClient: HelloClient,
    private val helloClient2: HelloClient2
) {

    fun getOne() = helloClient.getHello()

    fun getListWithThread() {
        helloClient.getHello()
        helloClient.getHello2()
    }

    fun getList() {
        log.info { "getList" }
        runBlocking {
            coroutineScope {
                val jobs = listOf(

                    async(Dispatchers.IO) {
                        log.info { "work1" }
                        helloClient.getHello()
                    },

                    async(Dispatchers.IO) {
                        log.info { "work2" }
                        helloClient2.getHello2()
                    }
                )

                jobs.awaitAll()
            }
        }
    }

    fun getListWithWorkerThread() {
        CoroutineScope(Dispatchers.IO).async {
            log.info { "work1" }
            helloClient.getHello()
        }

        CoroutineScope(Dispatchers.IO).async {
            log.info { "work2" }
            helloClient.getHello2()
        }
    }
}
