package com.vms.vms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VmsApplication

fun main(args: Array<String>) {
	runApplication<VmsApplication>(*args)
}
