package com.example.basic.config

import com.example.basic.config.resolver.CustomHttpHeaderResolver
import com.example.basic.core.extents.PublicInterceptor
import com.example.basic.core.extents.PublicRequestMappingHandlerMapping
import java.util.Locale
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor
import org.springframework.web.servlet.i18n.SessionLocaleResolver
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping

@Configuration
class WebMvcConfiguration : DelegatingWebMvcConfiguration() {
    override fun localeResolver() =
        SessionLocaleResolver()
            .apply {
                setDefaultLocale(Locale.KOREA)
            }

    @Bean
    fun localeChangeInterceptor() =
        LocaleChangeInterceptor()
            .apply {
                // 파라미터 이름
                paramName = "lang"
            }

    override fun createRequestMappingHandlerMapping(): RequestMappingHandlerMapping {
        return PublicRequestMappingHandlerMapping()
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(PublicInterceptor()).addPathPatterns("/public/**")
        registry.addInterceptor(localeChangeInterceptor())
        super.addInterceptors(registry)
    }

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(CustomHttpHeaderResolver())
    }
}
