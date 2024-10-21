package com.example.test

//import org.junit.jupiter.api.Test
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Mono
//import reactor.test.StepVerifier
//import reactor.test.StepVerifierOptions
//import reactor.util.context.Context


//class ContextTest {
//
////    var stepVerifierOptions: StepVerifierOptions = StepVerifierOptions.create()
////        .withInitialContext(Context.of("a", "b"))
////
////    //fails
////    @Test
////    fun testContextEmptyMono() {
////        StepVerifier.create(Mono.empty<T>(), stepVerifierOptions)
////            .expectAccessibleContext()
////            .contains("a", "b")
////            .then()
////            .verifyComplete()
////    }
////
////    @Test
////    fun testContextEmptyFlux() {
////        StepVerifier.create(Flux.empty<T>(), stepVerifierOptions)
////            .expectAccessibleContext()
////            .contains("a", "b")
////            .then()
////            .verifyComplete()
////    }
////
////    //passes
////    @Test
////    fun testContextEmptyMonoWithContextWrite() {
////        StepVerifier.create(Mono.empty<Any>().contextWrite(Context.of("a", "b")))
////            .expectAccessibleContext()
////            .contains("a", "b")
////            .then()
////            .verifyComplete()
////    }
////
////    @Test
////    fun testContextNonEmptyMono() {
////        StepVerifier.create(Mono.just<T>("item"), stepVerifierOptions)
////            .expectAccessibleContext()
////            .contains("a", "b")
////            .then()
////            .expectNext("item")
////            .verifyComplete()
////    }
////
////    @Test
////    fun testContextNonEmptyFlux() {
////        StepVerifier.create(Flux.just<T>("item"), stepVerifierOptions)
////            .expectAccessibleContext()
////            .contains("a", "b")
////            .then()
////            .expectNext("item")
////            .verifyComplete()
////    }
//}
