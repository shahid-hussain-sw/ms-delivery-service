package com.delivery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Main class that holds the main method.
 *
 */
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
