/**
 *
 */
package com.mevenk.webapp.config.spring;

import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.BASE_PACKAGE;
import static java.util.concurrent.Executors.newScheduledThreadPool;

import java.util.concurrent.Executor;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.mevenk.webapp.config.spring.messagesource.StaticDataMessageSource;

/**
 * @author venky
 *
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = BASE_PACKAGE)
public class MeVenkWebAppRootConfiguration implements SchedulingConfigurer {

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.scheduling.annotation.SchedulingConfigurer#configureTasks
	 * (org.springframework.scheduling.config.ScheduledTaskRegistrar)
	 */
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.setScheduler(taskExecutor());

	}

	@Bean(destroyMethod = "shutdown")
	public Executor taskExecutor() {
		return newScheduledThreadPool(10);
	}

	/*
	 *
	 * Bean name required to be "messageSource" to override default source trigger
	 * resolveCode() method inside custom MessageSource
	 */
	@Bean(name = "messageSource")
	public MessageSource staticDataMessageSource() {
		return new StaticDataMessageSource();
	}

}
