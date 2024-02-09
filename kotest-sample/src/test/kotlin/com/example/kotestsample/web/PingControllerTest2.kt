package com.example.kotestsample.web

import com.example.kotestsample.extensions.RestDocExtension
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class PingControllerTest2 : FunSpec() {

    lateinit var mockMvc: MockMvc

    init {

        extensions(SpringExtension, RestDocExtension())

        beforeTest {
            mockMvc = MockMvcBuilders.standaloneSetup(PingController())
                .build()
        }

//        test("test2") {
//
//            mockMvc.perform(get("/api/ping"))
//                .andExpect(status().isOk)
//                .andDo(print())
//        }
    }

}
