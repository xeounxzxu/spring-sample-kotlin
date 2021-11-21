package com.example.base

import mu.KotlinLogging
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.core.env.Environment
import java.net.InetAddress
import java.net.UnknownHostException
import java.util.*


@SpringBootApplication
class BaseApplication

private val log = KotlinLogging.logger {}

fun main(args: Array<String>) {
    val app = SpringApplication(BaseApplication::class.java)
    val env: Environment = app.run(args.toString()).environment
    logApplicationStartup(env)
}

/**
 * Log application startup </br>
 *
 * @param env Environment
 */
private fun logApplicationStartup(env: Environment) {

    val protocol = Optional.ofNullable(env.getProperty("server.ssl.key-store")).map { key -> "https" }.orElse("http")

    val serverPort = if (env.getProperty("server.port") == null) "8080" else env.getProperty("server.port")!!

    val contextPath = Optional.ofNullable(env.getProperty("server.servlet.context-path")).orElse("/")

    val applicationName = Optional.ofNullable(env.getProperty("spring.application.name")).orElse("")

    var hostAddress: String? = "localhost"

    try {
        hostAddress = InetAddress.getLocalHost().hostAddress
    } catch (e: UnknownHostException) {
        log.warn("The host name could not be determined, using `localhost` as fallback")
    }

    log.info(
        "\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}{}\n\t" +
                "External: \t{}://{}:{}{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
        applicationName,
        protocol,
        serverPort,
        contextPath,
        protocol,
        hostAddress,
        serverPort,
        contextPath,
        env.activeProfiles
    )

}

