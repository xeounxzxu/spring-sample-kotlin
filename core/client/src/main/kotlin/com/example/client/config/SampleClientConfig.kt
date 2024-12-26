package com.example.client.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import java.time.Duration
import java.util.concurrent.TimeUnit
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider

abstract class ClientConfigProperties(
    open val connectTimeout: Duration,
    open val readTimeout: Duration,
    open val writeTimeout: Duration,
    open val host: String,
    open val connectionPool: ConnectionPool,
)

abstract class ConnectionPool(
    val name: String,
    val maxConnections: Int,
    val maxIdleTime: Duration,
    val maxLifeTime: Duration,
    val pendingAcquireTimeout: Duration,
    val evictInBackground: Duration
)

abstract class ClientConfig2 {

    fun getDefaultClient(properties: ClientConfigProperties): WebClient {
        return WebClient.builder()
            .baseUrl(properties.host)
            .clientConnector(getDefaultClientConnector(properties))
            .exchangeStrategies(exchangeStrategies())
            // todo : custom filter add e.g. json logger filter and etc ..
            .build()
    }

    fun getDefaultClientConnector(properties: ClientConfigProperties): ReactorClientHttpConnector {

        val connectionProvider =
            ConnectionProvider.builder(properties.connectionPool.name)
                // 유지할 수 있는 connection pool 의 수
                .maxConnections(properties.connectionPool.maxConnections) // Number of pools that can be maintained
                // 사용하지 않는 상태의 커넥션 이 유지되는 시간
                .maxIdleTime(properties.connectionPool.maxIdleTime)
                // connection pool 에서 최대 수명 시간
                .maxLifeTime(properties.connectionPool.maxLifeTime) // Time for which an idle connection is maintained
                // connection poll 에서 사용할 수 있는 커넥션이 모두 사용중일 때 대기 시간
                .pendingAcquireTimeout(properties.connectionPool.pendingAcquireTimeout) //
                // 백그라운드에서 만료된 커넥션을 제거하는 주기
                .evictInBackground(properties.connectionPool.evictInBackground)
                // 마지막에 사용된 커낵션을 재사용함
                .lifo()
                .build()

        val httpClient = HttpClient.create(connectionProvider)
            .option(
                ChannelOption.CONNECT_TIMEOUT_MILLIS,
                properties.connectTimeout.toMillis().toInt()
            )
            .doOnConnected {
                it.addHandlerFirst(
                    ReadTimeoutHandler(
                        properties.readTimeout.toMillis(),
                        TimeUnit.MILLISECONDS
                    )
                )
                it.addHandlerLast(
                    WriteTimeoutHandler(
                        properties.writeTimeout.toMillis(),
                        TimeUnit.MILLISECONDS
                    )
                )
            }
            .keepAlive(true)

        return ReactorClientHttpConnector(httpClient)
    }

    private fun exchangeStrategies(): ExchangeStrategies {
        return ExchangeStrategies.builder()
            .codecs {
                with(it.defaultCodecs()) {
                    jackson2JsonEncoder(Jackson2JsonEncoder(defaultObjectMapper(), MediaType.APPLICATION_JSON))
                    jackson2JsonDecoder(Jackson2JsonDecoder(defaultObjectMapper(), MediaType.APPLICATION_JSON))
                }
            }.build()
    }

    private fun defaultObjectMapper(): ObjectMapper {
        return jacksonObjectMapper().registerKotlinModule()
            .apply {
                registerModules(Jdk8Module()) // todo : custom module add e.g. registerModules(custom())
                configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
                configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
                setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
            }
    }
}
