package com.example.base.config

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class BaseConfigurationTest {

    private var context: AnnotationConfigApplicationContext? = null

    @BeforeEach
    fun init() {

        context = AnnotationConfigApplicationContext().apply {
                this.scan("com.example.base.config")
                this.refresh()
            }
    }

    @Test
    fun `defaultYn Bean 생성 유무 체크`() {
        val defaultYn = context?.getBean("defaultYn") as Boolean
        Assertions.assertThat(defaultYn::class.java).isEqualTo(Boolean::class.java)
    }
}