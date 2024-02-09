package com.example.kotestsample.web

import com.example.kotestsample.extensions.RestDocExtension
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.assertions.print.print
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.restassured.http.ContentType
import io.restassured.module.mockmvc.RestAssuredMockMvc
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder

class PingControllerTest : FunSpec() {

    lateinit var mockMvc: MockMvcRequestSpecification

    private val restDocumentation = ManualRestDocumentation()

    private val converter = MappingJackson2HttpMessageConverter(ObjectMapper())

    private val controller = PingController()

    init {

        extensions(SpringExtension, RestDocExtension())

        beforeTest {

            val localMockMvc =
                MockMvcBuilders.standaloneSetup(controller)
                    .apply<StandaloneMockMvcBuilder>(
                        MockMvcRestDocumentation.documentationConfiguration(
                            this.restDocumentation
                        )
                    )
                    .setMessageConverters(converter)
                    .build()

            // todo : 요거 설정 안하면 ,,, 에러 발새
            this.restDocumentation.beforeTest(PingController::class.java, it.name.testName)

            mockMvc = RestAssuredMockMvc
                .given()
                .mockMvc(localMockMvc)
        }

        afterSpec {
            this.restDocumentation.afterTest();
        }

        test("test") {

            mockMvc
                .contentType(ContentType.JSON)
                .get("/api/ping")
                .then()
                .statusCode(200)
                .apply(
                    document(
                        "{class-name}/{method-name}",
                        PayloadDocumentation.responseFields(
                            PayloadDocumentation.fieldWithPath("status").type(JsonFieldType.STRING).description("상태")
                        )
                    )
                )
                .expect {
                    it.print()
                }

        }
    }
}

