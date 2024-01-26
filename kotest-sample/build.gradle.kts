tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}


dependencies {

    // kotest dependencies add
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.1.0")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.1.0")
    implementation("io.kotest:kotest-extensions-spring:4.4.3")
}
