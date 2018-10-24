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
@Order(2)
public class MethodLoggingTrigger extends BaseTrigger {

	private static final Logger LOG = getLogger(MethodLoggingTrigger.class);

	private static final String POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS = "allBeanMethodsExceptConfigAndTriggers()";

	@Pointcut("execution(* com.mevenk.webapp..*(..)) && !execution(* com.mevenk.webapp.trigger..*(..)) && !execution(* com.mevenk.webapp.config..*(..))")
	private void allBeanMethodsExceptConfigAndTriggers() {
		// Do Nothing
	}

	@Before(POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS)
	protected void logEnteringMethod(JoinPoint joinPoint) {
		generateJoinPointDetail(joinPoint);
		generateJoinPointDetailWithArguments(joinPoint);
		LOG.log(TRIGGER, "Entered " + joinPointDetailWithArguments);
	}

	@After(POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS)
	protected void logExitingMethod(JoinPoint joinPoint) {
		LOG.log(TRIGGER, "Exited " + joinPointDetail);
	}

	@AfterReturning(pointcut = POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS, returning = "retVal")
	protected void logIfMethodValueReturned(JoinPoint joinPoint, Object retVal) {
		LOG.log(TRIGGER, "Returned " + joinPointDetail + "|" + retVal);
	}

	@AfterThrowing(pointcut = POINT_CUT_ALL_BEAN_METHODS_EXCEPT_CONFIG_AND_TRIGGERS, throwing = "exception")
	protected void logIfMethodException(JoinPoint joinPoint, Exception exception) {
		LOG.log(TRIGGER, "Exception " + joinPointDetail + "|" + exception.getMessage());
	}
}
