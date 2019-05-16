/**
 * 
 */
package com.mevenk.webapp.config.spring.boot;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.mevenk.webapp.config.spring.MeVenkWebAppRootConfiguration;

/**
 * @author vkolisetty
 *
 */
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class MeVenkWebAppApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = new SpringApplicationBuilder(
				MeVenkWebAppRootConfiguration.class).run(args);

		System.out.println("Context: " + configurableApplicationContext.getDisplayName());

	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Beans:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}
