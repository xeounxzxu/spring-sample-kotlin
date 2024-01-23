import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("plugin.spring") apply false
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management")
}


allprojects {
    group = "${property("projectGroup")}"
    version = "${property("applicationVersion")}"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        sourceCompatibility = JavaVersion.VERSION_21
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        //thymeleaf
        implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.5.5")
        implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")

        //looger
        implementation("io.github.microutils:kotlin-logging-jvm:2.0.6")

        // jasypt
        implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:1.17")

//     mysql
//    implementation("mysql:mysql-connector-java")
        implementation("com.h2database:h2")

        // jpa
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "${project.property("javaVersion")}"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}
