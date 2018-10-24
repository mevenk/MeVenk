/**
 *
 */
package com.mevenk.webapp.trigger.controller.impl;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.TRIGGER;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import com.mevenk.webapp.trigger.controller.ControllerTrigger;

/**
 * @author venky
 *
 */
public class ControllerRequestTrigger extends ControllerTrigger {

	private static final Logger LOG = getLogger(ControllerRequestTrigger.class);

	@Override
	protected void logPreControllerRequest(JoinPoint joinPoint) {
		LOG.log(TRIGGER, "Performing Pre Request Activities {}", joinPointDetail);
	}

	@Override
	protected void logPostControllerRequest(JoinPoint joinPoint) {
		LOG.log(TRIGGER, "Performing Post Request Activities {}", joinPointDetail);
	}

}
