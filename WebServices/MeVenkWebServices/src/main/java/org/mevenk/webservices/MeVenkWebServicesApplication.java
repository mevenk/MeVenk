/**
 * 
 */
package org.mevenk.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author vkolisetty
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class MeVenkWebServicesApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MeVenkWebServicesApplication.class, args);

	}

}
