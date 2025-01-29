package com.example.client.config

import com.example.client.clients.HelloClient
import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.Duration
import org.apache.coyote.BadRequestException
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import reactor.netty.http.client.HttpClient

private val log = KotlinLogging.logger {}

@Configuration
class HelloClientConfig {

    companion object {
        const val URL = "http://localhost:5050"
    }


    @Bean
    fun helloClient(): HelloClient {
        val httpClient = HttpClient.create()
            .responseTimeout(Duration.ofMillis(30000)) // 30초 타임아웃 설정

        val builder = WebClient.builder()
            .baseUrl(URL)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .defaultStatusHandler(
                { status -> status.is4xxClientError }, // 4xx이면 true 반환
                { _ ->
                    log.warn { "400 exception" }
                    throw BadRequestException("400 exception")
                }
            )
            .defaultStatusHandler(
                { status -> status.is5xxServerError }, // 5xx이면 true 반환
                { _ ->
                    log.warn { "500 exception" }
                    throw RuntimeException("500 exception")
                }
            )

        val create = WebClientAdapter.create(builder.build())

        val builderFor = HttpServiceProxyFactory.builderFor(create)
            .build()

        return builderFor.createClient(HelloClient::class.java)
    }

}
