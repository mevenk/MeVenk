package com.mevenk.webapp.trigger;

import static com.mevenk.webapp.util.MeVenkWebAppStringUtil.isAnyStringEmptyOrNull;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.methodArgumentsAsString;
import static com.mevenk.webapp.util.ThreadContextUtil.resetCorrelationIdThreadContextWithCurrentDate;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.HYPHEN;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.UNDERSCORE;

import org.aspectj.lang.JoinPoint;

/**
 *
 * @author venky
 *
 */
public abstract class BaseTrigger {

	protected static final String ADVICE_AND = " && ";
	protected static final String ADVICE_OR = " || ";
	protected static final String ADVICE_ANY_ARGS = "args(..)";

	protected String joinPointDetail;
	protected String joinPointDetailWithArguments;

	/**
	 *
	 * @param proceedingJoinPoint
	 */
	protected String updateCorrelationIdThreadContext(JoinPoint joinPoint, String... correlationIdParameters) {
		StringBuilder stringBuilderCorreltionIdPrefix = new StringBuilder();
		stringBuilderCorreltionIdPrefix.append(joinPoint.getSignature().getName());
		for (String parameter : correlationIdParameters) {
			if (isAnyStringEmptyOrNull(parameter)) {
				continue;
			}
			stringBuilderCorreltionIdPrefix.append(UNDERSCORE + parameter);
		}
		return resetCorrelationIdThreadContextWithCurrentDate(stringBuilderCorreltionIdPrefix.toString());
	}

	/**
	 *
	 * @param joinPoint
	 * @return
	 */
	protected void generateJoinPointDetail(JoinPoint joinPoint) {
		StringBuilder stringBuilderJoinPoint = new StringBuilder();
		stringBuilderJoinPoint.append(joinPoint.getKind());
		stringBuilderJoinPoint.append(HYPHEN + joinPoint.toShortString());
		joinPointDetail = stringBuilderJoinPoint.toString();
	}

	/**
	 *
	 * @param joinPoint
	 * @return
	 */
	protected void generateJoinPointDetailWithArguments(JoinPoint joinPoint) {
		StringBuilder stringBuilderJoinPoint = new StringBuilder();
		stringBuilderJoinPoint.append(joinPoint.getKind());
		stringBuilderJoinPoint.append(joinPoint.toLongString());
		stringBuilderJoinPoint.append(methodArgumentsAsString(joinPoint.getArgs()));
		joinPointDetailWithArguments = stringBuilderJoinPoint.toString();
	}

}