package com.example.client.clients

import java.time.Duration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.invoker.HttpRequestValues
import org.springframework.web.service.invoker.HttpServiceProxyFactory
import org.springframework.web.service.invoker.ReactorHttpExchangeAdapter


interface HelloClient {

    @GetExchange("/v1/hello")
    fun getHello(): HelloDto
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
        val builder = WebClient.builder()
            .baseUrl(URL)
            .build()

        val create = WebClientAdapter.create(builder)
            .apply {
                blockTimeout = Duration.ofMillis(500)
            }

        val builderFor = HttpServiceProxyFactory.builderFor(create)
            .build()
        return builderFor.createClient(HelloClient::class.java)
    }
}
