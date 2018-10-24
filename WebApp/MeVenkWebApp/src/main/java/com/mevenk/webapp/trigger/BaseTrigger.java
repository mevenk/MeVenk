package com.mevenk.webapp.trigger;

import static com.mevenk.webapp.util.MeVenkWebAppUtil.objectArrayAsString;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.resetCorrelationIdThreadContext;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.HYPHEN;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 *
 * @author venky
 *
 */
public abstract class BaseTrigger {

	protected static final String ADVICE_AND = " && ";
	protected static final String ADVICE_OR = " || ";
	protected static final String ADVICE_ANY_ARGS = "args(..)";

	/**
	 *
	 * @param proceedingJoinPoint
	 */
	protected void updateCorrelationIdThreadContext(ProceedingJoinPoint proceedingJoinPoint) {
		StringBuilder stringBuilderCorreltionIdPrefix = new StringBuilder();
		stringBuilderCorreltionIdPrefix.append(proceedingJoinPoint.getSignature().getName());
		resetCorrelationIdThreadContext(stringBuilderCorreltionIdPrefix.toString());
	}

	/**
	 *
	 * @param joinPoint
	 * @return
	 */
	protected String generateJoinPointDetail(JoinPoint joinPoint) {
		StringBuilder stringBuilderJoinPoint = new StringBuilder();
		stringBuilderJoinPoint.append(joinPoint.getKind());
		stringBuilderJoinPoint.append(HYPHEN + joinPoint.toShortString());
		return stringBuilderJoinPoint.toString();
	}

	/**
	 *
	 * @param proceedingJoinPoint
	 * @return
	 */
	protected String generateProceedingJoinPointDetail(ProceedingJoinPoint proceedingJoinPoint) {
		StringBuilder stringBuilderProceedingJoinPoint = new StringBuilder();
		stringBuilderProceedingJoinPoint.append(proceedingJoinPoint.getKind());
		stringBuilderProceedingJoinPoint.append(HYPHEN + proceedingJoinPoint.toShortString());
		stringBuilderProceedingJoinPoint.append(HYPHEN + objectArrayAsString(proceedingJoinPoint.getArgs()));
		return stringBuilderProceedingJoinPoint.toString();
	}

}