package com.example.base.dto

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "thymeleaf")
data class ThymeleafDTO @ConstructorBinding constructor(
    var mode: String? = null,
    var prefix: String? = null,
    var suffix: String? = null,
    var cache: Boolean? = null,
    var checkTemplateLocation: Boolean? = null,
    var characterEncoding: String? = null
)
