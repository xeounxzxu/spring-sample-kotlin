package com.example.kotestsample.extensions

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.extensions.TestCaseExtension
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import io.kotest.extensions.spring.testContextManager
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import kotlinx.coroutines.withContext
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

const val docsRootPath : String = "docs/"

val RestDocExtension = RestDocTestExtension()

val converter = MappingJackson2HttpMessageConverter(ObjectMapper())

suspend fun mockMvc(
    controller: Any,
    httpMessageConverter: AbstractJackson2HttpMessageConverter? = converter
): MockMvcRequestSpecification {

    val mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .apply<StandaloneMockMvcBuilder>(
            MockMvcRestDocumentation.documentationConfiguration(
                manualRestDocumentation()
            )
        )
        .setMessageConverters(httpMessageConverter).alwaysDo<StandaloneMockMvcBuilder>(MockMvcResultHandlers.print())
        .build()

    return RestAssuredMockMvc.given().mockMvc(mockMvc)
}

suspend fun manualRestDocumentation(): ManualRestDocumentation {
    return coroutineContext[RestDocTestContextCoroutineContextElement]?.manualRestDocumentation
        ?: error("No ManualRestDocumentation defined in this coroutine context")
}


class RestDocTestContextCoroutineContextElement(
    val manualRestDocumentation: ManualRestDocumentation,
) : AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<RestDocTestContextCoroutineContextElement>
}

class RestDocTestExtension : TestCaseExtension {

    override suspend fun intercept(
        testCase: TestCase,
        execute: suspend (TestCase) -> TestResult
    ): TestResult {
        val manualRestDocumentation = ManualRestDocumentation()

        return withContext(RestDocTestContextCoroutineContextElement(manualRestDocumentation)) {
            testContextManager().beforeTestClass()
            testContextManager().prepareTestInstance(testCase.spec)
            manualRestDocumentation.beforeTest(testCase.javaClass::class.java, testCase.name.testName)
            val result = execute(testCase)
            manualRestDocumentation.afterTest()
            testContextManager().afterTestClass()
            result
        }
    }
}

