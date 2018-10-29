/**
 *
 */
package com.mevenk.webapp.trigger.polling;

import static com.mevenk.webapp.config.logger.MeVenkWebAppLogger.TRIGGER;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.mevenk.webapp.trigger.BaseTrigger;

/**
 * @author venky
 *
 */
@Aspect
public class PollingTrigger extends BaseTrigger {

	private static final Logger LOG = getLogger(PollingTrigger.class);

	private static final String POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION = "methodsWithScheduledAnnotation()";

	// *******************************POINTCUTS*******************************

	@Pointcut("@annotation(org.springframework.scheduling.annotation.Scheduled)")
	private void methodsWithScheduledAnnotation() {

	}

	// *******************************POINTCUTS-END*******************************

	// *******************************ADVICES*******************************

	// *******************************methodsWithRequestAnnotation*******************************

	@Before(POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION)
	protected void runSchedulerTriggerPreRequisites(JoinPoint joinPoint) {
		generateJoinPointDetail(joinPoint);
		generateJoinPointDetailWithArguments(joinPoint);
		LOG.log(TRIGGER, "Thread Context Key generated:" + updateCorrelationIdThreadContext(joinPoint));
	}

	@Before(POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION)
	protected void logPreSchedulerRequest(JoinPoint joinPoint) {

	}

	@After(POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION)
	protected void logPostSchedulerRequest(JoinPoint joinPoint) {

	}

	@AfterReturning(pointcut = POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION, returning = "retVal")
	protected void logIfReturnedSchedulerRequest(JoinPoint joinPoint, Object retVal) {

	}

	@AfterThrowing(pointcut = POINT_CUT_METHODS_WITH_SCHEDULED_ANNOTATION, throwing = "exception")
	protected void logIfExceptionSchedulerRequest(JoinPoint joinPoint, Exception exception) {

	}

	// *******************************methodsWithRequestAnnotation-END*******************************

	// *******************************ADVICES-END*******************************

}
