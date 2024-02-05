package com.example.kotestsample.web

import com.example.kotestsample.extensions.RestDocExtension
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
class PingControllerTest : DescribeSpec({



    describe("A get request on the profile resource with a valid id") {
        it("should return the profile dto") {



//            getWinTestClient()
//                .get()
//                .uri("/api/ping")
////                .header(HttpHeaders.CONTENT_TYPE, ApiVersion.V2, MediaType.APPLICATION_JSON_VALUE)
//                .exchange()
//                .expectStatus().isOk
//                .expectBody()
//                .jsonPath("id").isEqualTo("profileId")
//                .jsonPath("darkMode").isEqualTo(true)
//                .consumeWith(
//                    document(
//                        "GET/api/profiles/id",
//                        resourceDetails = resourceDetails()
//                            .responseSchema(Schema("profile"))
//                            .description("""This resource return the profile for the given id.""".trimMargin())
//                            .summary("Get the profile data."),
//                        snippets = arrayOf(
//                            requestHeaders(
//                                headerWithName("Content-Type")
//                                    .description("The GET is compatible with V2")
//                            ),
//                            pathParameters(
//                                parameterWithName("id")
//                                    .description(
//                                        "The unique id of the profile resource"
//                                    )
//                            ),
//                            responseFields(
//                                fieldWithPath("id").description("The unique id of the profile"),
//                                fieldWithPath("darkMode").description("Is the dark mode active"),
//                            )
//                        ),
//                    )
//                )
        }
    }
}) {
    override fun extensions() = listOf(RestDocExtension(), SpringExtension)

}

//suspend fun getWinTestClient(): WebTestClient =
//    WebTestClient
//        .bindToApplicationContext(testContextManager().testContext.applicationContext)
//        .configureClient()
//        .filter(WebTestClientRestDocumentation.documentationConfiguration(manualRestDocumentation()))
//        .build()
