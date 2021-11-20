package com.example.base.config

import com.example.base.web.dto.ThymeleafDTO
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver


@Configuration
class ThymeleafConfiguration {

    val thymeleafDTO: ThymeleafDTO

    constructor(thymeleafDTO: ThymeleafDTO) {
        this.thymeleafDTO = thymeleafDTO
    }

    /**
     * 타임리프 설정
     */
    @Bean
    fun templateResolver(): SpringResourceTemplateResolver? {
        val templateResolver = SpringResourceTemplateResolver()
        templateResolver.prefix = thymeleafDTO.prefix
        templateResolver.characterEncoding = thymeleafDTO.characterEncoding
        templateResolver.suffix = thymeleafDTO.suffix
        templateResolver.setTemplateMode(thymeleafDTO.mode)
        templateResolver.isCacheable = thymeleafDTO.cache!!
        return templateResolver
    }

}
