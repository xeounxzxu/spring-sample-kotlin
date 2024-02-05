package com.example.kotestsample.extensions

import io.kotest.core.extensions.TestCaseExtension
import io.kotest.core.test.TestCase
import io.kotest.extensions.spring.testContextManager
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope.coroutineContext
import kotlinx.coroutines.withContext
import org.springframework.restdocs.ManualRestDocumentation
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

suspend fun manualRestDocumentation(): ManualRestDocumentation =
    coroutineContext[RestDocTestContextCoroutineContextElement]?.manualRestDocumentation
        ?: error("No ManualRestDocumentation defined in this coroutine context")


class RestDocTestContextCoroutineContextElement(
    val manualRestDocumentation: ManualRestDocumentation
) : AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<RestDocTestContextCoroutineContextElement>
}

class RestDocExtension : TestCaseExtension {
    private val manualRestDocumentation = ManualRestDocumentation()

    override suspend fun intercept(
        testCase: TestCase,
        execute: suspend (TestCase) -> io.kotest.core.test.TestResult
    ): io.kotest.core.test.TestResult {
        manualRestDocumentation.beforeTest(testCase.javaClass::class.java, testCase.name.testName)

        return withContext(RestDocTestContextCoroutineContextElement(manualRestDocumentation)) {
            testContextManager().beforeTestClass()
            testContextManager().prepareTestInstance(testCase.spec)
            val result = execute(testCase)
            manualRestDocumentation.afterTest()
            testContextManager().afterTestClass()
            result
        }
    }
}
