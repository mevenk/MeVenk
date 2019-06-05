/**
 * 
 */
package org.mevenk.webservices;

import java.util.Arrays;

import org.mevenk.webservices.logger.Logger;
import org.mevenk.webservices.logger.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author vkolisetty
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class MeVenkWebServicesApplication {

	private static final Logger LOG = LoggerFactory.getlogger(MeVenkWebServicesApplication.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MeVenkWebServicesApplication.class, args);

	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			LOG.info("Beans: " + Arrays.toString(ctx.getBeanDefinitionNames()));

		};
	}

}