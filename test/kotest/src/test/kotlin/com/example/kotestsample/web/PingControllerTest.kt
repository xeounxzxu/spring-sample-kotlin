package com.example.kotestsample.web

import com.example.example.restdocs.RestDocTestExtension
import com.example.example.restdocs.converter
import com.example.example.restdocs.docsRootPath
import com.example.example.restdocs.mockMvc
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.restassured.http.ContentType
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class PingControllerTest : FunSpec() {

    private val controller = PingController()

    init {

        extensions(SpringExtension, RestDocTestExtension())

        context("GET /api/ping") {

            test("정상적으로 ping 을 보낸다") {

                mockMvc(controller, converter)
                    .contentType(ContentType.JSON)
                    .get("/api/ping")
                    .then()
                    .statusCode(200)
                    .apply(
                        document(
                            "${docsRootPath}/core/api-ping",
                            responseFields(
                                fieldWithPath("status")
                                    .type(JsonFieldType.STRING).description("상태")
                            )
                        )
                    )
            }
        }
    }
}

