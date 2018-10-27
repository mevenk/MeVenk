/**
 *
 */
package com.mevenk.webapp.trigger.controller;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.TRIGGER;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.getHTTPSessionId;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.VERTICAL_BAR;
import static org.apache.logging.log4j.LogManager.getLogger;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import com.mevenk.webapp.trigger.BaseTrigger;

/**
 * @author venky
 *
 */
@Aspect
@Order(1)
public class ControllerTrigger extends BaseTrigger {

	private static final Logger LOG = getLogger(ControllerTrigger.class);

	@Autowired
	private HttpServletRequest httpRequest;

	private static final String POINT_CUT_METHODS_WITH_REQUEST_ANNOTATION = "methodsWithRequestAnnotation()";

	// *******************************POINTCUTS*******************************

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping)")
	private void methodsWithRequestAnnotation() {

	}

	// *******************************POINTCUTS-END*******************************

	// *******************************ADVICES*******************************

	// *******************************methodsWithRequestAnnotation*******************************

	@Before(POINT_CUT_METHODS_WITH_REQUEST_ANNOTATION)
	protected void runTriggerPreRequisites(JoinPoint joinPoint) {
		generateJoinPointDetail(joinPoint);
		generateJoinPointDetailWithArguments(joinPoint);
		LOG.log(TRIGGER, "Thread Context Key generated:"
				+ updateCorrelationIdThreadContext(joinPoint, getHTTPSessionId(httpRequest)));
		LOG.log(TRIGGER, "HTTP Request:" + httpRequest.getMethod() + VERTICAL_BAR + httpRequest.getServletPath()
				+ VERTICAL_BAR + httpRequest.getContextPath() + VERTICAL_BAR + httpRequest);
	}

	@Before(POINT_CUT_METHODS_WITH_REQUEST_ANNOTATION)
	protected void logPreControllerRequest(JoinPoint joinPoint) {

	}

	@After(POINT_CUT_METHODS_WITH_REQUEST_ANNOTATION)
	protected void logPostControllerRequest(JoinPoint joinPoint) {
	}

	@AfterReturning(pointcut = POINT_CUT_METHODS_WITH_REQUEST_ANNOTATION, returning = "retVal")
	protected void logIfReturnedControllerRequest(JoinPoint joinPoint, Object retVal) {
	}

	@AfterThrowing(pointcut = POINT_CUT_METHODS_WITH_REQUEST_ANNOTATION, throwing = "exception")
	protected void logIfExceptionControllerRequest(JoinPoint joinPoint, Exception exception) {
	}

	// *******************************methodsWithRequestAnnotation-END*******************************

	// *******************************ADVICES-END*******************************

}
