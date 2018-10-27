/**
 *
 */
package com.mevenk.webapp.config.logger;

import static org.apache.logging.log4j.Level.forName;

import org.apache.logging.log4j.Level;

/**
 * @author venky
 *
 */
public final class MeVenkWebAppLogger {

	private MeVenkWebAppLogger() throws IllegalAccessException {
		throw new IllegalAccessException("Logger Class!!");
	}

	public static final String THREAD_CONTEXT_KEY_WEB_APP_CORRELATION_ID = "MeVenkWebAppCorrelationId";

	public static final String THREAD_CONTEXT_KEY_ATTRIBUTE_SESSION_ATTRIBUTE_NAME_SESSION_ID = "THREAD_CONTEXT_KEY_ATTRIBUTE_SESSION_ATTRIBUTE_NAME_SESSION_ID";

	/*
	 * OFF 0 | FATAL 100 | ERROR 200 | WARN 300 | INFO 400 | POLLING 470 | TRIGGER
	 * 490 | DEBUG 500 | CONFIG 590 | TRACE 600 | ALL Integer.MAX_VALUE
	 */

	private static final String LOG_LEVEL_POLLING = "POLLING";
	public static final Level POLLING = forName(LOG_LEVEL_POLLING, 470);

	private static final String LOG_LEVEL_TRIGGER = "TRIGGER";
	public static final Level TRIGGER = forName(LOG_LEVEL_TRIGGER, 490);

	private static final String LOG_LEVEL_CONFIG = "CONFIG";
	public static final Level CONFIG = forName(LOG_LEVEL_CONFIG, 590);
}
