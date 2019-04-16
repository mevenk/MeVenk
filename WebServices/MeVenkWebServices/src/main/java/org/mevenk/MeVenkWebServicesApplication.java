/**
 * 
 */
package org.mevenk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vkolisetty
 *
 */
@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class MeVenkWebServicesApplication {

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MeVenkWebServicesApplication.class, args);

	}

}
