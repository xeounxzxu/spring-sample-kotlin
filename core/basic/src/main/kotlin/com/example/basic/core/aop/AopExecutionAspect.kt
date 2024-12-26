package com.example.basic.core.aop

import com.example.basic.service.Response
import com.fasterxml.jackson.databind.ObjectMapper
import java.lang.reflect.Method
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class AopExecutionAspect(
    private val objectMapper: ObjectMapper
) {

    @Around("@annotation(com.example.basic.core.aop.AopExecution)")
    fun logAround(joinPoint: ProceedingJoinPoint): Response<*> {

        // 메서드 실행 전
        // 메서드 실행 전: 파라미터 이름과 값 가져오기
        val method = getMethod(joinPoint)
        val aopExecution = method?.getAnnotation(AopExecution::class.java)
        val parameterNames = method?.parameters?.map { it.name }
        val args = joinPoint.args

        val isUse = parameterNames?.indexOfFirst { it == aopExecution?.isUseName }?.let {
            args[it] as Boolean
        } ?: false

        // ObjectPassing
        val requestString = parameterNames?.indexOfFirst { it == aopExecution?.requestStringName }?.let {
            args[it] as String
        } ?: ""

        if (isUse) {
            return Response(common = "", 1L)
        }

        // 메서드 실행 및 예외 처리
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
