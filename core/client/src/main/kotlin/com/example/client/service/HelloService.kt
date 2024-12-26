package com.example.client.service

import com.example.client.clients.HelloClient
import com.example.client.clients.HelloDto
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

    fun getList(): List<HelloDto> {
        return runBlocking {
            listOf(
                async(Dispatchers.IO) {
                    log.info { "work1" }
                    helloClient.getHello()
                },
                async(Dispatchers.IO) {
                    log.info { "work2" }
                    helloClient.getHello2(
                        num = 1
                    )
                }
            ).awaitAll()
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
