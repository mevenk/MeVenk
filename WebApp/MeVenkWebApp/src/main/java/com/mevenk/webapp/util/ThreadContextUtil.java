/**
 *
 */
package com.mevenk.webapp.util;

import static com.mevenk.webapp.util.MeVenkWebAppStringUtil.isAnyStringEmptyOrNull;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
import static com.mevenk.webapp.util.MeVenkWebAppUtil.argumentsAsAppendableString;
import static com.mevenk.webapp.util.StaticData.correlationIdDateFormatPatternLength;
import static com.mevenk.webapp.util.StaticData.simpleDateFormatCorrelationId;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.HYPHEN;
import static com.mevenk.webapp.util.constants.MeVenkWebAppConstants.UNDERSCORE;
import static org.apache.logging.log4j.ThreadContext.get;
import static org.apache.logging.log4j.ThreadContext.put;

import java.util.Date;

import javax.servlet.http.HttpSession;

/**
 * @author venky
 *
 */
public final class ThreadContextUtil {

	private static final IllegalArgumentException ILLEGAL_ARGUMENT_EXCEPTION_THREAD_CONTEXT_CORRELATION_ID_NULL = new IllegalArgumentException(
			"Correlation Id cannot be NULL");

	/* ThreadContext keys */

	private static final String THREAD_CONTEXT_KEY_WEB_APP_CORRELATION_ID = "MeVenkWebAppCorrelationId";

	private static final String THREAD_CONTEXT_KEY_ATTRIBUTE_SESSION_ATTRIBUTE_NAME_SESSION_ID = "THREAD_CONTEXT_KEY_ATTRIBUTE_SESSION_ATTRIBUTE_NAME_SESSION_ID";

	/* ThreadContext keys - END */

	/**
	 *
	 * @throws IllegalAccessException
	 */
	private ThreadContextUtil() throws IllegalAccessException {
		throw ILLEGAL_ACCESS_EXCEPTION_UTILITY_CLASS;
	}

	/**
	 *
	 */
	public static void removeCorrelationIdThreadContext() {
		put(THREAD_CONTEXT_KEY_WEB_APP_CORRELATION_ID, null);
	}

	/**
	 *
	 * @param correlationId
	 * @return
	 */
	public static String setCorrelationIdThreadContext(String correlationId) {
		if (isAnyStringEmptyOrNull(correlationId)) {
			throw ILLEGAL_ARGUMENT_EXCEPTION_THREAD_CONTEXT_CORRELATION_ID_NULL;
		}
		put(THREAD_CONTEXT_KEY_WEB_APP_CORRELATION_ID, correlationId);
		return get(THREAD_CONTEXT_KEY_WEB_APP_CORRELATION_ID);
	}

	/**
	 *
	 * @param correlationIdPrefix
	 */
	public static String resetCorrelationIdThreadContextWithCurrentDate(String correlationIdPrefix) {
		put(THREAD_CONTEXT_KEY_WEB_APP_CORRELATION_ID,
				correlationIdPrefix + UNDERSCORE + simpleDateFormatCorrelationId.format(new Date()));
		return get(THREAD_CONTEXT_KEY_WEB_APP_CORRELATION_ID);
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public static String retreiveThreadContextValue(String key) {
		return get(key);
	}

	/**
	 *
	 * @return
	 */
	public static String retreiveThreadContextValueSessionId() {
		return retreiveThreadContextValue(THREAD_CONTEXT_KEY_ATTRIBUTE_SESSION_ATTRIBUTE_NAME_SESSION_ID);
	}

	/**
	 *
	 * @param session
	 */
	public static void populateThreadContextRequiredSessionAttributes(HttpSession session) {
		put(THREAD_CONTEXT_KEY_ATTRIBUTE_SESSION_ATTRIBUTE_NAME_SESSION_ID, session.getId());
	}

	/**
	 *
	 * @param parameters
	 */
	public static void addParametersToCorrelationId(Object... parameters) {
		String correlationIdExisting = get(THREAD_CONTEXT_KEY_WEB_APP_CORRELATION_ID);
		int lengthDateWithUnderScore = correlationIdDateFormatPatternLength + 1;
		int lengthExistingCorrelationId = correlationIdExisting.length();
		String unserscoreAndDate = correlationIdExisting
				.substring(lengthExistingCorrelationId - lengthDateWithUnderScore);
		String correlationIdWithoutUnserscoreAndDate = correlationIdExisting.substring(0,
				lengthExistingCorrelationId - unserscoreAndDate.length());
		StringBuilder stringBuilderCorrelationIdModified = new StringBuilder();
		stringBuilderCorrelationIdModified.append(correlationIdWithoutUnserscoreAndDate);
		stringBuilderCorrelationIdModified.append(HYPHEN + argumentsAsAppendableString(true, parameters) + HYPHEN);
		stringBuilderCorrelationIdModified.append(unserscoreAndDate);
		put(THREAD_CONTEXT_KEY_WEB_APP_CORRELATION_ID, stringBuilderCorrelationIdModified.toString());
	}

}
