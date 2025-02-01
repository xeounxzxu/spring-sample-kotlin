package com.example.client.service

import com.example.client.clients.HelloClient
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}

@Service
class HelloService(
    private val helloClient: HelloClient
) {

    fun getOne() = helloClient.getHello()

    fun getListWithThread() {
        helloClient.getHello()
        helloClient.getHello2()
    }

    fun getList() {
        log.info { "getList" }
        runBlocking {
                val job1 = async(Dispatchers.IO) {
                    log.info { "work1" }
                    helloClient.getHello()
                }
            val job2 = async(Dispatchers.IO) {
                log.info { "work2" }
                helloClient.getHello2()
            }
            listOf(job1, job2).awaitAll()
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
