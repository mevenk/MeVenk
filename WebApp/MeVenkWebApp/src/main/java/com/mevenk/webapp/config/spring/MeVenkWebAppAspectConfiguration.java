/**
 *
 */
package com.mevenk.webapp.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.mevenk.webapp.trigger.MethodLoggingTrigger;

/**
 * @author venky
 *
 */
@Configuration
@Import(MeVenkWebAppRootConfiguration.class)
@EnableAspectJAutoProxy(exposeProxy = true)
public class MeVenkWebAppAspectConfiguration {

	@Bean
	public MethodLoggingTrigger loggingAspect() {
		return new MethodLoggingTrigger();
	}

}
