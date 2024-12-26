package com.example.basic.core.aop

@Target(AnnotationTarget.FUNCTION) // 함수에 적용
@Retention(AnnotationRetention.RUNTIME) // 런타임 동안 유지
annotation class AopExecution(
    val isUseName: String = "isUse",
    val requestStringName: String = "requestString",
)
