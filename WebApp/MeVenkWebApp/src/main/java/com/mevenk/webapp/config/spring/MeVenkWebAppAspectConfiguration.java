/**
 *
 */
package com.mevenk.webapp.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.mevenk.webapp.trigger.LoggingAspect;

/**
 * @author venky
 *
 */
@Configuration
@Import(MeVenkWebAppRootConfiguration.class)
@EnableAspectJAutoProxy(exposeProxy = true)
public class MeVenkWebAppAspectConfiguration {

	@Bean
	public LoggingAspect loggingAspect() {
		return new LoggingAspect();
	}

}
