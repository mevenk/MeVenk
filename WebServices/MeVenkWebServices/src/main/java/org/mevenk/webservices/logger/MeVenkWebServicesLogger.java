/**
 * 
 */
package org.mevenk.webservices.logger;

import org.apache.logging.log4j.LogManager;

/**
 * @author vkolisetty
 *
 */
final class MeVenkWebServicesLogger implements Logger {

	private final org.apache.logging.log4j.Logger logger;

	MeVenkWebServicesLogger(final Class<?> clazz) {
		logger = LogManager.getLogger(clazz);
	}

	@Override
	public void debug(Object message) {
		logger.debug(message);

	}

	@Override
	public void debug(String message) {
		logger.debug(message);

	}

	@Override
	public void debug(String message, Object... params) {
		logger.debug(message, params);

	}

	@Override
	public void error(Object message) {
		logger.error(message);

	}

	@Override
	public void error(String message) {
		logger.error(message);

	}

	@Override
	public void error(String message, Object... params) {
		logger.error(message, params);

	}

	@Override
	public void error(Object message, Throwable throwable) {
		logger.error(message, throwable);

	}

	@Override
	public void error(String message, Throwable throwable) {
		logger.error(message, throwable);

	}

	@Override
	public void error(String message, Throwable throwable, Object... params) {
		logger.error(message, throwable, params);

	}

	@Override
	public void fatal(Object message) {
		logger.fatal(message);

	}

	@Override
	public void fatal(String message) {
		logger.fatal(message);

	}

	@Override
	public void fatal(String message, Object... params) {
		logger.fatal(message, params);

	}

	@Override
	public void info(Object message) {
		logger.info(message);

	}

	@Override
	public void info(String message) {
		logger.info(message);

	}

	@Override
	public void info(String message, Object... params) {
		logger.info(message, params);

	}

	@Override
	public void trace(Object message) {
		logger.trace(message);

	}

	@Override
	public void trace(String message) {
		logger.trace(message);

	}

	@Override
	public void trace(String message, Object... params) {
		logger.trace(message, params);

	}

	@Override
	public void warn(Object message) {
		logger.warn(message);

	}

	@Override
	public void warn(String message) {
		logger.warn(message);

	}

	@Override
	public void warn(String message, Object... params) {
		logger.warn(message, params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MeVenkWebServicesLogger [logger=" + logger + "]";
	}

}
