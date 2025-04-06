package com.chaincharts

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ChainchartsApiApplication

fun main(args: Array<String>) {
	runApplication<ChainchartsApiApplication>(*args)
}
