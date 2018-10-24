/**
 *
 */
package com.mevenk.webapp.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.mevenk.webapp.trigger.MethodLoggingTrigger;
import com.mevenk.webapp.trigger.controller.ControllerTrigger;
import com.mevenk.webapp.trigger.controller.impl.ControllerRequestTrigger;

/**
 * @author venky
 *
 */
@Configuration
@Import(MeVenkWebAppRootConfiguration.class)
@EnableAspectJAutoProxy(exposeProxy = true)
public class MeVenkWebAppAspectConfiguration {

	@Bean(name = "methodLoggingTrigger")
	public MethodLoggingTrigger methodLoggingTrigger() {
		return new MethodLoggingTrigger();
	}

	@Bean(name = "controllerRequestTrigger")
	public ControllerTrigger controllerRequestTrigger() {
		return new ControllerRequestTrigger();
	}

}
