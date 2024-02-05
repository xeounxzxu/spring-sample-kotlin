tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}


dependencies {

    compileOnly("jakarta.servlet:jakarta.servlet-api")
    compileOnly("org.springframework.boot:spring-boot-starter-test")
    api("org.springframework.restdocs:spring-restdocs-mockmvc")
    api("org.springframework.restdocs:spring-restdocs-restassured")
    api("io.rest-assured:spring-mock-mvc")

    // kotest dependencies add
    testImplementation("io.kotest:kotest-runner-junit5-jvm:${property("kotestVersion")}")
    testImplementation("io.kotest:kotest-assertions-core-jvm:${property("kotestVersion")}")
    testImplementation("io.kotest:kotest-property:${property("kotestVersion")}")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:${property("extensionsSpringVersion")}")
}

