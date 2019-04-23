/**
 * 
 */
package org.mevenk.webservices.logger;

import static org.apache.logging.log4j.Level.forName;

import java.util.LinkedHashMap;

import org.apache.logging.log4j.Level;
import org.mevenk.webservices.util.MeVenkWebServicesUtil;

/**
 * @author vkolisetty
 *
 */
public final class LoggerFactory {

	/*
	 * OFF 0 | FATAL 100 | ERROR 200 | WARN 300 | INFO 400 | POLLING 470 | TRIGGER
	 * 490 | DEBUG 500 | CONFIG 590 | TRACE 600 | ALL Integer.MAX_VALUE
	 */

	private static enum LogLevel {

		DEBUG {
			@Override
			Level getLLevel() {
				return Level.DEBUG;
			}
		},
		ERROR {
			@Override
			Level getLLevel() {
				return Level.ERROR;
			}
		},
		FATAL {
			@Override
			Level getLLevel() {
				return Level.FATAL;
			}
		},
		INFO {
			@Override
			Level getLLevel() {
				return Level.INFO;
			}
		},
		TRACE {
			@Override
			Level getLLevel() {
				return Level.TRACE;
			}
		},
		WARN {
			@Override
			Level getLLevel() {
				return Level.WARN;
			}
		},
		POLLING {
			@Override
			Level getLLevel() {
				return forName("POLLING", 470);
			}
		},
		TRIGGER {
			@Override
			Level getLLevel() {
				return forName("TRIGGER", 490);
			}
		},
		CONFIG {
			@Override
			Level getLLevel() {
				return forName("CONFIG", 590);
			}
		};

		abstract Level getLLevel();

	}

	static {
		setLoggerConfiguration();
	}

	private static LinkedHashMap<Class<?>, Logger> loggerObjects = new LinkedHashMap<Class<?>, Logger>();
	private static LinkedHashMap<String, LogLevel> loggers = new LinkedHashMap<String, LogLevel>();

	/**
	 * 
	 * @throws Throwable
	 */
	private LoggerFactory() throws Throwable {
		MeVenkWebServicesUtil.throwExceptionInstantiationNotAllowed(LoggerFactory.class);
	}

	private static void setLoggerConfiguration() {

	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	public static Logger getlogger(Class<?> clazz) {

		return addOrReturnLogger(clazz);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 */
	private static Logger addOrReturnLogger(Class<?> clazz) {
		if (!loggerObjects.containsKey(clazz)) {
			loggerObjects.put(clazz, new MeVenkWebServicesLogger(clazz));
		}
		return loggerObjects.get(clazz);
	}

}
