package com.example.basic.config.resolver

import jakarta.servlet.http.HttpServletRequest
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class CustomHttpHeaderResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType == CustomHeader::class.java
    }

    @OptIn(ExperimentalEncodingApi::class)
    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): CustomHeader {
        return (webRequest.nativeRequest as HttpServletRequest).let { request ->
            CustomHeader(
                id = checkNotNull(request.getHeader(CUSTOM_USER_ID_KEY)).toLong(),
                name = request.getHeader(CUSTOM_USER_NAME_KEY)?.let {
                    Base64.Default.decode(it).decodeToString()
                }
            )
        }
    }

    companion object {
        val CUSTOM_USER_ID_KEY = "custom-user-id"
        val CUSTOM_USER_NAME_KEY = "custom-user-name"
    }
}
