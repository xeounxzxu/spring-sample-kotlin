tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    // 레디스
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    // lettuce
    implementation("io.lettuce:lettuce-core:6.4.0.RELEASE")
}
