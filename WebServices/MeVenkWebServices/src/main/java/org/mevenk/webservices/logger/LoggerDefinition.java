/**
 * 
 */
package org.mevenk.webservices.logger;

import java.util.Objects;

import org.apache.logging.log4j.core.config.LoggerConfig;

/**
 * @author vkolisetty
 *
 */
class LoggerDefinition {

	private LoggerConfig loggerConfig;

	/**
	 * @param loggerName
	 * @param logLevel
	 * @param additivity
	 */
	LoggerDefinition(final String name, final LogLevel logLevel, final boolean additive) {
		this.loggerConfig = new LoggerConfig(name, logLevel.getLevel(), additive);
	}

	/**
	 * @return the loggerConfig
	 */
	final LoggerConfig getLoggerConfig() {
		return loggerConfig;
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof LoggerDefinition)
				&& (((LoggerDefinition) obj).loggerConfig.getName().equals(loggerConfig.getName()));
	}

	@Override
	public int hashCode() {
		return Objects.hash(loggerConfig);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LoggerDefinition [loggerConfig=" + loggerConfig + "]";
	}

}
