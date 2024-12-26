package com.example.client.clients

import java.time.Duration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import reactor.netty.Connection
import reactor.netty.http.client.HttpClient


interface HelloClient {

    @GetExchange("/v1/hello")
    fun getHello(): HelloDto

    @GetExchange("/v1/hello2")
    fun getHello2(
        @RequestParam(name = "num")
        num: Int? = null
    ): HelloDto
}


data class HelloDto(
    val kr: String,
    val en: String
)


@Configuration
class ClientConfig {

    companion object {
        const val URL = "http://localhost:5050"
    }

    @Bean
    fun helloClient(): HelloClient {

        /*
        val httpClient = HttpClient.create()
            .responseTimeout(Duration.ofMillis(500)) //read timeout
        */

        val httpClient =
            HttpClient.create()
                .doOnConnected { conn: Connection ->
//                    conn.addHandlerFirst(
//                        ReadTimeoutHandler(500, TimeUnit.MILLISECONDS)
//                    )
                }
//                .doOnChannelInit { observer: ConnectionObserver?, channel: Channel, remoteAddress: SocketAddress? ->
//                    channel.pipeline()
//                        .addFirst(LoggingHandler("reactor.netty.examples"))
//                }

        val builder = WebClient.builder()
            .baseUrl(URL)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()

        val create = WebClientAdapter.create(builder)
            .apply {
                // Subscribe to this Mono and block until a next signal is received or a timeout expires.
                // 이 모노를 구독하고 다음 신호가 수신되거나 타임아웃이 만료될 때까지 차단하세요.
                blockTimeout = Duration.ofMillis(1000)
            }

        val builderFor = HttpServiceProxyFactory.builderFor(create)
            .build()
        return builderFor.createClient(HelloClient::class.java)
    }
}
