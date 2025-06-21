tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {

    runtimeOnly ("io.netty:netty-resolver-dns-native-macos:4.1.104.Final:osx-aarch_64")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:1.6.4")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

//    // https://mvnrepository.com/artifact/com.fasterxml.jackson.module/jackson-module-kotlin
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.0")
//    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.0")
//    implementation("com.fasterxml.jackson.core:jackson-annotations:2.18.0")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.0")
//    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.18.0")
//
//    // https://mvnrepository.com/artifact/io.projectreactor/reactor-core
//    implementation("io.projectreactor:reactor-core:3.6.11")
//
//    // https://mvnrepository.com/artifact/io.projectreactor/reactor-test
//    testImplementation("io.projectreactor:reactor-test:3.1.0.RELEASE")
}
