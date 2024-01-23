package com.example.base.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BaseConfiguration {

    @Bean("defaultYn")
    fun defaultBoolean() = true
}
