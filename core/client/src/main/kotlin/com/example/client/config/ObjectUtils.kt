package com.example.client.config

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.reflect.Type
import org.springframework.core.ParameterizedTypeReference
//
//inline fun <reified T : Any> ObjectMapper.decode(
//    content: String,
//    parameterizedTypeRef: ParameterizedTypeReference<T>
//): T = readValue(content, parameterizedTypeRef.toJacksonTypeRef())
//
//inline fun <reified T : Any> parameterizedTypeRef(): ParameterizedTypeReference<T> {
//    val theType = object : ParameterizedTypeReference<T>() {}.type
//    return object : ParameterizedTypeReference<T>() {
//        override fun getType(): Type = theType
//    }
//}
//
//fun ParameterizedTypeReference<*>.toJacksonTypeRef(): TypeReference<*> {
//    val theType = this.type
//    return object : TypeReference<Any>() {
//        override fun getType(): Type = theType
//    }
//}
//
//fun ObjectMapper.encode(value: Any?): String = writeValueAsString(value)
