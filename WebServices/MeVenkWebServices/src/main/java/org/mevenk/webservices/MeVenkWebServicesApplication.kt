package org.mevenk.webservices

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * @author vkolisetty
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
open class MeVenkWebServicesApplication

/**
 * @param args
 */
fun main(args: Array<String>) {
	runApplication<MeVenkWebServicesApplication>(*args)

}