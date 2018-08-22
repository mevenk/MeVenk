/**
 *
 */
package com.mevenk.webapp.config.spring;

import static java.util.concurrent.Executors.newScheduledThreadPool;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author venky
 *
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.mevenk.webapp")
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

}
