package com.example.liveredismode.api

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
class RedisTestController(
    private val redisConnectionFactory: LettuceConnectionFactory,
) {

    @GetMapping("/test")
    fun getTest() {
        val template: RedisTemplate<String, String> = RedisTemplate()
        template.connectionFactory = redisConnectionFactory
        template.setDefaultSerializer(StringRedisSerializer.UTF_8)
        template.afterPropertiesSet()

        template.opsForValue().set("foo", "bar")
        logger.info { "value at foo ${template.opsForValue().get("foo")}" }
        redisConnectionFactory.destroy()
    }
}
