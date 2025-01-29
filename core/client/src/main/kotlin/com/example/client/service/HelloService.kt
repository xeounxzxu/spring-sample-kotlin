package com.example.client.service

import com.example.client.clients.HelloClient
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
    private val helloClient: HelloClient
) {

    fun getOne() = helloClient.getHello()

    fun getListWithThread() {
        helloClient.getHello()
        helloClient.getHello2()
    }

    fun getList() {
        runBlocking {
            try {
                coroutineScope {
                    val job1 = async(Dispatchers.IO) { helloClient.getHello() }
                    val job2 = async(Dispatchers.IO) { helloClient.getHello2() }

                    listOf(job1, job2).awaitAll() // 하나라도 예외 발생 시 즉시 취소
                }
            } catch (e: Exception) {
                println("Exception occurred: ${e.message}")
                throw e
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
