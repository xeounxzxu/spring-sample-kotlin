package com.example.base

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VirtualThreadApplication

fun main(args: Array<String>) {
    runApplication<VirtualThreadApplication>(*args)
}
