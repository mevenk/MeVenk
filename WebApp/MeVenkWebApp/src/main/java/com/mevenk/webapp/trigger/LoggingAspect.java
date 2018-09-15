/**
 *
 */
package com.mevenk.webapp.trigger;

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
import org.springframework.core.annotation.Order;

/**
 * @author venky
 *
 */
@Aspect
@Order(1)
public class LoggingAspect {

	private static final Logger LOG = getLogger(LoggingAspect.class);

	private static final String POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS = "allBeanMethodsExceptConfigAndTriggers()";

	@Pointcut("execution(* com.mevenk.webapp..*(..)) && !execution(* com.mevenk.webapp.trigger..*(..)) && !execution(* com.mevenk.webapp.config..*(..))")
	private void allBeanMethodsExceptConfigAndTriggers() {
		// Do Nothing
	}

	@Before(POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS)
	protected void logEnteringMethod(JoinPoint joinPoint) {
		LOG.log(TRIGGER, "Entered " + generateJointPointDetail(joinPoint));
	}

	@After(POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS)
	protected void logExitingMethod(JoinPoint joinPoint) {
		LOG.log(TRIGGER, "Exited " + generateJointPointDetail(joinPoint));
	}

	@AfterReturning(pointcut = POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS, returning = "retVal")
	protected void logIfMethodValueReturned(JoinPoint joinPoint, Object retVal) {
		LOG.log(TRIGGER, "Returned " + generateJointPointDetail(joinPoint) + "|" + retVal);
	}

	@AfterThrowing(pointcut = POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS, throwing = "exception")
	protected void logIfMethodException(JoinPoint joinPoint, Exception exception) {
		LOG.log(TRIGGER, "Exception " + generateJointPointDetail(joinPoint) + "|" + exception.getMessage());
	}

	private String generateJointPointDetail(JoinPoint joinPoint) {
		StringBuilder stringBuilderJoinPoint = new StringBuilder();
		stringBuilderJoinPoint.append(joinPoint.getKind());
		stringBuilderJoinPoint.append("-" + joinPoint.toShortString());
		return stringBuilderJoinPoint.toString();

	}
}
