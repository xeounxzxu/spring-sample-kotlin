package com.example.client.config

import com.example.client.service.Response
import java.lang.reflect.Method
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component

//val objectMapper: ObjectMapper = ObjectMapper().registerModule(KotlinModule())

@Aspect
@Component
class AopExecutionAspect {

    val responseType = object : ParameterizedTypeReference<Response<*>>()

    @Around("@annotation(com.example.client.config.AopExecution)")
    fun logAround(joinPoint: ProceedingJoinPoint): Response<*> {

        // 메서드 실행 전
        // 메서드 실행 전: 파라미터 이름과 값 가져오기
        val methodName = joinPoint.signature.name
        val method = getMethod(joinPoint)
        val parameterNames = method?.parameters?.map { it.name }
        val args = joinPoint.args

        println("Before executing method: $methodName")

        val index = parameterNames?.indexOfFirst { it == "isUse" }
        val index2 = parameterNames?.indexOfFirst { it == "requestString" }

        val isUse = index?.let { args[it] } as Boolean
        val requestString = index2?.let { args[it] } ?: ""

        if (isUse) {
            return Response(common = "", 1L)
        }

        // 메서드 실행
        return try {
            joinPoint.proceed() as Response<*>
        } catch (e: Exception) {
            e.printStackTrace()
            Response(common = "", 1L)
        }
    }

    // ProceedingJoinPoint에서 메서드 정보를 가져오는 유틸리티 함수
    private fun getMethod(joinPoint: ProceedingJoinPoint): Method? {
        val signature = joinPoint.signature
        if (signature is org.aspectj.lang.reflect.MethodSignature) {
            return signature.method
        }
        return null
    }
}


//inline fun <reified T> ObjectMapper.readValue(s: String): T = this.readValue(s, object : TypeReference<T>() {})
