package com.devbuild.robot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class RobotApplication

fun main(args: Array<String>) {
    SpringApplication.run(RobotApplication::class.java, *args)
}
