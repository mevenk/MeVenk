/**
 * 
 */
package org.mevenk.webservices.logger;

import java.util.LinkedHashSet;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.mevenk.webservices.util.MeVenkWebServicesUtil;

/**
 * @author vkolisetty
 *
 */
final class LoggerDirectory {

	private static final LinkedHashSet<LoggerDefinition> LOGGER_DEFINITIONS = new LinkedHashSet<LoggerDefinition>();

	static {
		setLoggerConfiguration();
	}

	/**
	 * 
	 */
	private LoggerDirectory() throws Throwable {

		// Prevent instantiation
		MeVenkWebServicesUtil.throwExceptionInstantiationNotAllowed(LoggerDirectory.class);

	}

	/**
	 * 
	 */
	private static void setLoggerConfiguration() {

		updateLoggerlevelMap();
		addLogLvels();

	}

	/**
	 * 
	 */
	private static void updateLoggerlevelMap() {

		LOGGER_DEFINITIONS.add(new LoggerDefinition("org.mevenk.webservices.controller", LogLevel.DEBUG, false));
		LOGGER_DEFINITIONS.add(new LoggerDefinition("org.mevenk.webservices.service", LogLevel.INFO, false));

	}

	/**
	 * 
	 */
	private static void addLogLvels() {

		final LoggerContext loggerContext = (LoggerContext) LoggerContext.getContext(false);
		final Configuration config = loggerContext.getConfiguration();

		for (LoggerDefinition definition : LOGGER_DEFINITIONS) {
			LoggerConfig loggerConfig = definition.getLoggerConfig();
			config.addLogger(loggerConfig.getName(), loggerConfig);
			Level level = loggerConfig.getLevel();
			loggerConfig.setLevel(level);
		}

		loggerContext.updateLoggers();

	}

}
